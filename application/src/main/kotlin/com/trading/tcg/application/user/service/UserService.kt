package com.trading.tcg.application.user.service

import com.trading.tcg.application.user.dto.request.DeleteUserCommand
import com.trading.tcg.application.user.dto.request.FindUserQuery
import com.trading.tcg.application.user.dto.request.LoginUserCommand
import com.trading.tcg.application.user.dto.request.RegisterUserCommand
import com.trading.tcg.application.user.dto.response.UserDto
import com.trading.tcg.application.user.port.`in`.UserUseCase
import com.trading.tcg.application.user.port.out.UserPersistencePort
import com.trading.tcg.global.exception.CustomException
import com.trading.tcg.global.jwt.dto.request.CreateTokenCommand
import com.trading.tcg.global.jwt.dto.response.JwtToken
import com.trading.tcg.global.jwt.service.JwtTokenProvider
import com.trading.tcg.global.util.EncryptUtil.encodeToSha512
import com.trading.tcg.user.domain.User
import com.trading.tcg.user.domain.UserRole
import com.trading.tcg.user.exception.UserErrorCode
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@RequiredArgsConstructor
class UserService(
    private val userPersistencePort: UserPersistencePort,
    private val jwtTokenProvider: JwtTokenProvider
): UserUseCase {
    @Transactional(readOnly = true)
    override fun findMe(query: FindUserQuery): UserDto {
        val user = userPersistencePort.findById(query.userId)
            .orElseThrow { CustomException(UserErrorCode.NOT_FOUND_USER) }

        val userDto = UserDto(
            id = user.id!!,
            email = user.email
        )

        return userDto
    }

    @Transactional
    override fun register(command: RegisterUserCommand): JwtToken {
        val user = User(
            email = command.email,
            password = encodeToSha512(command.password),
            role = UserRole.GUEST
        )

        userPersistencePort.findByEmail(user.email)
            .ifPresent { throw CustomException(UserErrorCode.DUPLICATED_EMAIL) }

        userPersistencePort.save(user)

        val createTokenCommand = CreateTokenCommand(
            user = user
        )

        val token = jwtTokenProvider.createToken(createTokenCommand)

        return token
    }

    @Transactional
    override fun login(command: LoginUserCommand): JwtToken {
        val user = userPersistencePort.findByEmail(command.email)
            .orElseThrow { throw CustomException(UserErrorCode.NOT_MATCH_USER) }

        if (user.password != encodeToSha512(command.password)) {
            throw CustomException(UserErrorCode.NOT_MATCH_USER)
        }

        val createTokenCommand = CreateTokenCommand(
            user = user
        )

        val token = jwtTokenProvider.createToken(createTokenCommand)

        return token
    }

    @Transactional
    override fun delete(command: DeleteUserCommand) {
        val user = userPersistencePort.findById(command.userId)
            .orElseThrow { throw CustomException(UserErrorCode.NOT_FOUND_USER) }

        userPersistencePort.delete(user)
    }
}
