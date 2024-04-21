package com.trading.tcg.global.security

import com.trading.tcg.global.exception.CustomException
import com.trading.tcg.global.jwt.service.JwtTokenValidator
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lombok.RequiredArgsConstructor
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
@RequiredArgsConstructor
class JwtAuthenticationFilter(
    private val jwtTokenValidator: JwtTokenValidator,
    private val userDetailsService: UserDetailsService,
) : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = request.getHeader("Authorization")

        try {
            if (token != null && !token.equals("null")) {
                val decodedToken = jwtTokenValidator.validate(token)

                val userDetails = userDetailsService.loadUserByUsername(decodedToken.email)
                val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)

                SecurityContextHolder.getContext().authentication = authentication
            }
        } catch (e: CustomException) {
            request.setAttribute("exception", e.errorCode)
            return
        }

        filterChain.doFilter(request, response)
    }
}
