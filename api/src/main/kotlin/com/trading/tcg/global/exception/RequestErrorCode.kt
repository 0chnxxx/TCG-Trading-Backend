package com.trading.tcg.global.exception

enum class RequestErrorCode(
    override val statusCode: Int,
    override val errorCode: String,
    override val errorMessage: String
): ErrorCode {
    MISSING_REQUIRED_PARAMETER(400, "MISSING_REQUIRED_PARAMETER", "필수 파라미터가 입력되지 않았습니다."),
    OUT_OF_PAGE(400, "OUT_OF_PAGE", "페이지 번호가 범위를 벗어났습니다."),
    OUT_OF_SIZE(400, "OUT_OF_SIZE", "페이지 크기가 범위를 벗어났습니다."),
    UNAUTHORIZED_REQUEST(401, "UNAUTHORIZED", "인증되지 않았습니다."),
    FORBIDDEN_REQUEST(403, "FORBIDDEN_REQUEST", "권한이 없습니다."),
    NOT_FOUND_API(404, "NOT_FOUND_API", "API가 존재하지 않습니다.");
}
