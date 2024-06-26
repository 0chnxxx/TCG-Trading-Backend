package com.trading.tcg.global.exception

enum class ServiceErrorCode(
    private val statusCode: Int,
    private val errorMessage: String
) : ErrorCode {
    NOT_FOUND(404, "유저가 존재하지 않습니다."),
    BLANK_EMAIL(400, "이메일이 입력되지 않았습니다."),
    OUT_OF_RANGE_EMAIL(400, "이메일이 글자수 범위를 벗어났습니다."),
    INCORRECT_EMAIL(409, "이메일 형식이 올바르지 않습니다."),
    DUPLICATED_EMAIL(409, "중복된 이메일입니다."),
    BLANK_PASSWORD(400, "비밀번호가 입력되지 않았습니다."),
    OUT_OF_RANGE_PASSWORD(400, "비밀번호가 글자수 범위를 벗어났습니다."),
    INCORRECT_PASSWORD(409, "비밀번호 형식이 올바르지 않습니다."),
    NOT_MATCH_USER(400, "아이디 혹은 비밀번호가 일치하지 않습니다."),

    NOT_FOUND_CARD(404, "카드가 존재하지 않습니다."),

    OUT_OF_PAGE(400, "페이지 번호가 범위를 벗어났습니다."),
    OUT_OF_SIZE(400, "페이지 크기가 범위를 벗어났습니다."),

    MALFORMED_JWT(401, "손상된 토큰입니다."),
    EXPIRED_JWT(401, "만료된 토큰입니다."),
    UNSUPPORTED_JWT(401, "지원하지 않는 토큰입니다."),
    WRONG_SIGNATURE_JWT(401, "시그니처 검증에 실패한 토큰입니다."),
    ILLEGAL_ARGUMENT_JWT(401, "잘못된 토큰 입력입니다."),

    UNAUTHORIZED(401, "인증되지 않았습니다."),
    FORBIDDEN(403, "권한이 없습니다.");

    override fun getStatusCode() = statusCode
    override fun getErrorCode() = this.name
    override fun getErrorMessage() = errorMessage
}
