package com.trading.tcg.global.jwt.service

import com.trading.tcg.global.exception.CustomException
import com.trading.tcg.global.jwt.domain.JwtErrorCode
import com.trading.tcg.global.jwt.dto.response.DecodedToken
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.MalformedJwtException
import io.jsonwebtoken.UnsupportedJwtException
import io.jsonwebtoken.security.SignatureException
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.nio.charset.StandardCharsets

@Component
class JwtTokenValidator {
    @Value("\${auth.jwt.secret-key}")
    private lateinit var SECRET_KEY: String

    fun validate(token: String): DecodedToken {
        try {
            val decodedToken = Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY.toByteArray(StandardCharsets.UTF_8))
                .build()
                .parseClaimsJws(token)

            return DecodedToken(
                email = decodedToken.body.subject
            )
        } catch (e: ExpiredJwtException) {
            throw CustomException(JwtErrorCode.EXPIRED_JWT)
        } catch (e: UnsupportedJwtException) {
            throw CustomException(JwtErrorCode.UNSUPPORTED_JWT)
        } catch (e: SignatureException) {
            throw CustomException(JwtErrorCode.WRONG_SIGNATURE_JWT)
        } catch (e: MalformedJwtException) {
            throw CustomException(JwtErrorCode.MALFORMED_JWT)
        } catch (e: IllegalArgumentException) {
            throw CustomException(JwtErrorCode.ILLEGAL_ARGUMENT_JWT)
        }
    }
}
