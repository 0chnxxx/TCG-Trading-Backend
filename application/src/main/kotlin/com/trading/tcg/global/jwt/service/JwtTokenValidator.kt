package com.trading.tcg.global.jwt.service

import com.trading.tcg.application.user.domain.UserProvider
import com.trading.tcg.global.exception.CustomException
import com.trading.tcg.global.exception.ServiceErrorCode
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
                email = decodedToken.body.subject,
                provider = UserProvider.valueOf(decodedToken.body.audience)
            )
        } catch (e: ExpiredJwtException) {
            throw CustomException(ServiceErrorCode.EXPIRED_JWT)
        } catch (e: UnsupportedJwtException) {
            throw CustomException(ServiceErrorCode.UNSUPPORTED_JWT)
        } catch (e: SignatureException) {
            throw CustomException(ServiceErrorCode.WRONG_SIGNATURE_JWT)
        } catch (e: MalformedJwtException) {
            throw CustomException(ServiceErrorCode.MALFORMED_JWT)
        } catch (e: IllegalArgumentException) {
            throw CustomException(ServiceErrorCode.ILLEGAL_ARGUMENT_JWT)
        }
    }
}
