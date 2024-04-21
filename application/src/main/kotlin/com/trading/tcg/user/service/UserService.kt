package com.trading.tcg.user.service

import com.trading.tcg.global.dto.Response
import com.trading.tcg.global.exception.CustomException
import com.trading.tcg.global.exception.ServiceErrorCode
import com.trading.tcg.global.jwt.dto.request.CreateTokenCommand
import com.trading.tcg.global.jwt.dto.response.JwtToken
import com.trading.tcg.global.jwt.service.JwtTokenProvider
import com.trading.tcg.user.domain.User
import com.trading.tcg.user.domain.UserProvider
import com.trading.tcg.user.domain.UserRole
import com.trading.tcg.user.dto.request.LoginUserCommand
import com.trading.tcg.user.dto.request.RegisterUserCommand
import com.trading.tcg.user.port.`in`.LoginUserUseCase
import com.trading.tcg.user.port.`in`.RegisterUserUseCase
import com.trading.tcg.user.port.out.UserPersistencePort
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
@RequiredArgsConstructor
class UserService(
    private val userPersistencePort: UserPersistencePort,
    private val jwtTokenProvider: JwtTokenProvider
) : RegisterUserUseCase, LoginUserUseCase {
    override fun register(command: RegisterUserCommand): Response<JwtToken> {
        var user = User(
            email = command.email,
            password = command.password,
            role = UserRole.GUEST
        )

        user.checkDuplicateEmail(userPersistencePort)
        user = userPersistencePort.save(user)

        val token = jwtTokenProvider.createToken(CreateTokenCommand(user, UserProvider.USER))

        return Response.of(
            data = token
        )
    }

    override fun login(command: LoginUserCommand): Response<JwtToken> {
        val user = userPersistencePort.findByEmail(command.email)

        if (user == null) {
            throw CustomException(ServiceErrorCode.NOT_MATCH_USER)
        }

        if (user.password != command.password) {
            throw CustomException(ServiceErrorCode.NOT_MATCH_USER)
        }

        val token = jwtTokenProvider.createToken(CreateTokenCommand(user, UserProvider.USER))

        return Response.of(
            data = token
        )
    }
}
