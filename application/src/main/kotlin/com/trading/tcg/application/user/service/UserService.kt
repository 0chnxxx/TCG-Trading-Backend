package com.trading.tcg.application.user.service

import com.trading.tcg.application.user.domain.User
import com.trading.tcg.application.user.domain.UserRole
import com.trading.tcg.application.user.dto.request.LoginUserCommand
import com.trading.tcg.application.user.dto.request.RegisterUserCommand
import com.trading.tcg.application.user.port.`in`.UserUseCase
import com.trading.tcg.application.user.port.out.UserPersistencePort
import com.trading.tcg.global.dto.Response
import com.trading.tcg.global.exception.CustomException
import com.trading.tcg.global.exception.ServiceErrorCode
import com.trading.tcg.global.jwt.dto.request.CreateTokenCommand
import com.trading.tcg.global.jwt.dto.response.JwtToken
import com.trading.tcg.global.jwt.service.JwtTokenProvider
import com.trading.tcg.global.util.EncryptUtil.encodeToSha512
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@RequiredArgsConstructor
class UserService(
    private val userPersistencePort: UserPersistencePort,
    private val jwtTokenProvider: JwtTokenProvider
) : UserUseCase {
    @Transactional
    override fun register(command: RegisterUserCommand): Response<JwtToken> {
        var user = User(
            email = command.email,
            password = encodeToSha512(command.password),
            role = UserRole.GUEST
        )

        val duplicatedUser = userPersistencePort.findByEmail(user.email)

        if (duplicatedUser != null) {
            throw CustomException(ServiceErrorCode.DUPLICATED_EMAIL)
        }

        user = userPersistencePort.save(user)

        val createTokenCommand = CreateTokenCommand(
            user = user
        )

        val token = jwtTokenProvider.createToken(createTokenCommand)

        return Response.of(
            data = token
        )
    }

    @Transactional
    override fun login(command: LoginUserCommand): Response<JwtToken> {
        val user = userPersistencePort.findByEmail(command.email)

        if (user == null) {
            throw CustomException(ServiceErrorCode.NOT_MATCH_USER)
        }

        if (user.password != encodeToSha512(command.password)) {
            throw CustomException(ServiceErrorCode.NOT_MATCH_USER)
        }

        val createTokenCommand = CreateTokenCommand(
            user = user
        )

        val token = jwtTokenProvider.createToken(createTokenCommand)

        return Response.of(
            data = token
        )
    }
}
