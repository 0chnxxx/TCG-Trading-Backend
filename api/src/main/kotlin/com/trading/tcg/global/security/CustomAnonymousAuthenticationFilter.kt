package com.trading.tcg.global.security

import com.trading.tcg.global.dto.HttpProvider
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lombok.RequiredArgsConstructor
import org.springframework.security.authentication.AnonymousAuthenticationToken
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.util.*

@Component
@RequiredArgsConstructor
class CustomAnonymousAuthenticationFilter : OncePerRequestFilter() {
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        if (SecurityContextHolder.getContext().authentication == null) {
            val provider = HttpProvider()

            val authentication = AnonymousAuthenticationToken(
                UUID.randomUUID().toString(),
                provider,
                AuthorityUtils.createAuthorityList("ROLE_ANONYMOUS")
            )

            SecurityContextHolder.getContext().authentication = authentication
        }

        filterChain.doFilter(request, response)
    }
}
