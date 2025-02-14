package com.trading.tcg.global.security

import com.trading.tcg.application.user.port.out.UserPersistencePort
import com.trading.tcg.global.dto.HttpProvider
import com.trading.tcg.global.exception.CustomException
import com.trading.tcg.global.exception.ApplicationErrorCode
import com.trading.tcg.global.jwt.service.JwtTokenValidator
import com.trading.tcg.user.exception.UserErrorCode
import jakarta.servlet.http.HttpServletRequest
import lombok.RequiredArgsConstructor
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
@RequiredArgsConstructor
class CustomUserDetailsService(
    private val userPersistencePort: UserPersistencePort,
) : UserDetailsService {
    override fun loadUserByUsername(email: String): UserDetails {
        val user = userPersistencePort.findByEmail(email)
            .orElseThrow { throw CustomException(UserErrorCode.NOT_FOUND_USER) }

        return HttpProvider(user)
    }
}
