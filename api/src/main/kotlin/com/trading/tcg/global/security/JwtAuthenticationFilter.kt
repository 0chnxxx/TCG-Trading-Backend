package com.trading.tcg.global.security

import com.fasterxml.jackson.databind.ObjectMapper
import com.trading.tcg.global.dto.ErrorResponse
import com.trading.tcg.global.exception.CustomException
import com.trading.tcg.global.jwt.service.JwtTokenValidator
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lombok.RequiredArgsConstructor
import org.springframework.http.MediaType
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.nio.charset.StandardCharsets

@Component
@RequiredArgsConstructor
class JwtAuthenticationFilter(
    private val jwtTokenValidator: JwtTokenValidator,
    private val userDetailsService: UserDetailsService,
    private val objectMapper: ObjectMapper
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
            val errorResponse = ErrorResponse(
                path = request.requestURI,
                errorCode = e.errorCode.errorCode,
                errorMessage = e.errorCode.errorMessage
            )

            response.status = e.errorCode.statusCode
            response.contentType = MediaType.APPLICATION_JSON_VALUE
            response.characterEncoding = StandardCharsets.UTF_8.name()

            val json = objectMapper.writeValueAsString(errorResponse)

            response.writer.use { writer ->
                writer.write(json)
            }

            return
        }

        filterChain.doFilter(request, response)
    }
}
