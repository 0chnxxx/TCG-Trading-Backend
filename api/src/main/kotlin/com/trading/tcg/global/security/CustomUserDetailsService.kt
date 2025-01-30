package com.trading.tcg.global.security

import com.trading.tcg.application.user.port.out.UserPersistencePort
import com.trading.tcg.global.dto.HttpProvider
import com.trading.tcg.global.exception.CustomException
import com.trading.tcg.global.exception.ServiceErrorCode
import com.trading.tcg.global.jwt.service.JwtTokenValidator
import jakarta.servlet.http.HttpServletRequest
import lombok.RequiredArgsConstructor
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
@RequiredArgsConstructor
class CustomUserDetailsService(
    private val request: HttpServletRequest,
    private val userPersistencePort: UserPersistencePort,
    private val jwtTokenValidator: JwtTokenValidator
) : UserDetailsService {
    override fun loadUserByUsername(email: String?): UserDetails {
        val token = request.getHeader("Authorization")
        val decodedToken = jwtTokenValidator.validate(token)
        val user = userPersistencePort.findByEmail(decodedToken.email) ?: throw CustomException(ServiceErrorCode.NOT_FOUND)

        return HttpProvider(user)
    }
}
