package com.trading.tcg.global.exception

enum class ApplicationErrorCode(
    private val statusCode: Int,
    private val errorMessage: String
): ErrorCode {
    // 공통
    UNAUTHORIZED(401, "인증되지 않았습니다."),
    FORBIDDEN(403, "권한이 없습니다."),
    NOT_FOUND(404, "엔드포인트가 존재하지 않습니다."),

    // 요청
    BLANK_REQUEST_PARAMETER(400, "필수 파라미터가 입력되지 않았습니다."),
    INVALID_ORDER(400, "유효한 정렬 대상이 아닙니다."),
    INVALID_SORT(400, "유효한 정렬 방식이 아닙니다."),
    INVALID_TYPE(400, "유효한 타입이 아닙니다."),
    INVALID_STATUS(400, "유효한 상태가 아닙니다."),
    INVALID_TAB(400, "유효한 탭이 아닙니다."),
    OUT_OF_PAGE(400, "페이지 번호가 범위를 벗어났습니다."),
    OUT_OF_SIZE(400, "페이지 크기가 범위를 벗어났습니다."),

    // 토큰
    MALFORMED_JWT(401, "손상된 토큰입니다."),
    EXPIRED_JWT(401, "만료된 토큰입니다."),
    UNSUPPORTED_JWT(401, "지원하지 않는 토큰입니다."),
    WRONG_SIGNATURE_JWT(401, "시그니처 검증에 실패한 토큰입니다."),
    ILLEGAL_ARGUMENT_JWT(401, "잘못된 토큰 입력입니다.");

    override fun getStatusCode() = statusCode
    override fun getErrorCode() = this.name
    override fun getErrorMessage() = errorMessage
}
