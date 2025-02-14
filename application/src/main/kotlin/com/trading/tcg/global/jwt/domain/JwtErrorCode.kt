package com.trading.tcg.global.jwt.domain

import com.trading.tcg.global.exception.ErrorCode

enum class JwtErrorCode(
    override val statusCode: Int,
    override val errorCode: String,
    override val errorMessage: String
): ErrorCode {
    MALFORMED_JWT(401, "MALFORMED_JWT", "손상된 토큰입니다."),
    EXPIRED_JWT(401, "EXPIRED_JWT", "만료된 토큰입니다."),
    UNSUPPORTED_JWT(401, "UNSUPPORTED_JWT", "지원하지 않는 토큰입니다."),
    WRONG_SIGNATURE_JWT(401, "WRONG_SIGNATURE_JWT", "시그니처 검증에 실패한 토큰입니다."),
    ILLEGAL_ARGUMENT_JWT(401, "ILLEGAL_ARGUMENT_JWT", "잘못된 토큰 입력입니다.");
}
