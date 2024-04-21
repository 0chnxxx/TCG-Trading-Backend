package com.trading.tcg.global.config

import com.fasterxml.jackson.databind.ObjectMapper
import com.trading.tcg.global.dto.ErrorResponse
import com.trading.tcg.global.exception.ServiceErrorCode
import com.trading.tcg.global.security.CustomAnonymousAuthenticationFilter
import com.trading.tcg.global.security.JwtAuthenticationFilter
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lombok.RequiredArgsConstructor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.*
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.factory.PasswordEncoderFactories
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.access.AccessDeniedHandler
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.RequestMatcher
import org.springframework.web.cors.CorsConfiguration
import org.springframework.web.cors.CorsConfigurationSource
import org.springframework.web.cors.CorsUtils
import org.springframework.web.cors.UrlBasedCorsConfigurationSource

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
class SecurityConfig(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter,
    private val customAnonymousAuthenticationFilter: CustomAnonymousAuthenticationFilter,
    private val objectMapper: ObjectMapper
) {
    //SecurityFilterChain 설정
    @Bean
    @Throws(Exception::class)
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        //HttpSecurity 기본 설정
        http
            .httpBasic { config: HttpBasicConfigurer<HttpSecurity> ->
                config.disable()
            }
            .formLogin { config: FormLoginConfigurer<HttpSecurity> ->
                config.disable()
            }
            .csrf { config: CsrfConfigurer<HttpSecurity> ->
                config.disable()
            }
            .cors { config: CorsConfigurer<HttpSecurity> ->
                config.configurationSource(corsConfigurationSource())
            }
            .sessionManagement { config: SessionManagementConfigurer<HttpSecurity> ->
                config.sessionCreationPolicy(
                    SessionCreationPolicy.STATELESS
                )
            }
            .authorizeHttpRequests { config: AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry ->
                config.requestMatchers(RequestMatcher { request: HttpServletRequest ->
                    CorsUtils.isPreFlightRequest(
                        request
                    )
                }).permitAll()
                config.anyRequest().permitAll()
            }
            .anonymous { config: AnonymousConfigurer<HttpSecurity> ->
                config.disable()
            }
            .exceptionHandling { config: ExceptionHandlingConfigurer<HttpSecurity> ->
                config
                    .authenticationEntryPoint(authenticationEntryPoint)
                    .accessDeniedHandler(accessDeniedHandler)
            }
            .addFilterAt(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)
            .addFilterAfter(customAnonymousAuthenticationFilter, AnonymousAuthenticationFilter::class.java)

        return http.build()
    }

    private val authenticationEntryPoint =
        AuthenticationEntryPoint { request: HttpServletRequest, response: HttpServletResponse, authException: org.springframework.security.core.AuthenticationException? ->
            val errorCode = ServiceErrorCode.UNAUTHORIZED

            val errorResponse = ErrorResponse.of(
                path = request.requestURI,
                errorCode = errorCode.getErrorCode(),
                errorMessage = errorCode.getErrorMessage()
            )

            val json: String = objectMapper.writeValueAsString(errorResponse)

            response.status = HttpStatus.UNAUTHORIZED.value()
            response.contentType = "application/json;charset=UTF-8"
            response.writer.print(json)
        }

    private val accessDeniedHandler =
        AccessDeniedHandler { request: HttpServletRequest, response: HttpServletResponse, accessDeniedException: org.springframework.security.access.AccessDeniedException? ->
            val errorCode = ServiceErrorCode.FORBIDDEN

            val errorResponse = ErrorResponse.of(
                path = request.requestURI,
                errorCode = errorCode.getErrorCode(),
                errorMessage = errorCode.getErrorMessage()
            )

            val json: String = objectMapper.writeValueAsString(errorResponse)

            response.status = HttpStatus.FORBIDDEN.value()
            response.contentType = "application/json;charset=UTF-8"
            response.writer.print(json)
        }

    //AuthenticationManager 설정
    @Bean
    fun authenticationManager(authenticationConfiguration: AuthenticationConfiguration): AuthenticationManager {
        return authenticationConfiguration.getAuthenticationManager()
    }

    //PasswordEncoder 설정
    @Bean
    fun passwordEncoder(): PasswordEncoder {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder()
    }

    //CORS 정책 설정
    @Bean
    fun corsConfigurationSource(): CorsConfigurationSource {
        val configuration = CorsConfiguration()
        configuration.allowCredentials = true
        configuration.addAllowedOriginPattern("*")
        configuration.addAllowedHeader("*")
        configuration.addAllowedMethod("*")
        configuration.addExposedHeader("*")
        val source = UrlBasedCorsConfigurationSource()
        source.registerCorsConfiguration("/**", configuration)
        return source
    }
}
