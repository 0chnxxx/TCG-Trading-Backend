package com.trading.tcg.global.jwt.service

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jws
import io.jsonwebtoken.Jwts
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets

@Component
class JwtTokenValidator {
    @Value("\${auth.jwt.secret-key}")
    private lateinit var SECRET_KEY: String

    fun validate(token: String): Jws<Claims>? {
        return Jwts.parserBuilder()
            .setSigningKey(SECRET_KEY.toByteArray(StandardCharsets.UTF_8))
            .build()
            .parseClaimsJws(token)
    }
}
