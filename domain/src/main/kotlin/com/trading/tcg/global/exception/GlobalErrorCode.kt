package com.trading.tcg.global.exception

enum class GlobalErrorCode(
    override val statusCode: Int,
    override val errorCode: String,
    override val errorMessage: String
): ErrorCode {
    INVALID_SORT(404, "INVALID_SORT", "유효한 정렬 방식이 아닙니다.");
}
