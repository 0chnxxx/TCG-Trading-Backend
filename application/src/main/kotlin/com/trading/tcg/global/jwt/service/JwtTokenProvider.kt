package com.trading.tcg.global.jwt.service

import com.trading.tcg.global.jwt.dto.request.CreateTokenCommand
import com.trading.tcg.global.jwt.dto.response.JwtToken
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets
import java.util.*

@Component
class JwtTokenProvider {
    @Value("\${auth.jwt.secret-key}")
    private lateinit var SECRET_KEY: String
    private var ACCESS_EXPIRATION_TIME: Long = 2 * 60 * 60 * 1000L
    private var REFRESH_EXPIRATION_TIME: Long = 14 * 24 * 60 * 60 * 1000L

    fun createToken(command: CreateTokenCommand): JwtToken {
        val accessToken = accessToken(command)
        val refreshToken = refreshToken(command)
        return JwtToken(accessToken, refreshToken)
    }

    private fun accessToken(command: CreateTokenCommand): String {
        return Jwts.builder()
            .setHeaderParam("typ", "JWT")
            .setHeaderParam("alg", "HS512")
            .setClaims(Jwts.claims()
                .setSubject(command.user.email)
                .setAudience(command.provider.name))
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + ACCESS_EXPIRATION_TIME))
            .setIssuer("TCG-TRADING")
            .signWith(Keys.hmacShaKeyFor(SECRET_KEY.toByteArray(StandardCharsets.UTF_8)))
            .compact()
    }

    private fun refreshToken(command: CreateTokenCommand): String {
        return Jwts.builder()
            .setHeaderParam("typ", "JWT")
            .setHeaderParam("alg", "HS512")
            .setClaims(Jwts.claims()
                .setSubject(command.user.email)
                .setAudience(command.provider.name))
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + REFRESH_EXPIRATION_TIME))
            .setIssuer("TCG-TRADING")
            .signWith(Keys.hmacShaKeyFor(SECRET_KEY.toByteArray(StandardCharsets.UTF_8)))
            .compact()
    }
}
