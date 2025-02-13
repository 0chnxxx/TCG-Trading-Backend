package com.trading.tcg.user.exception

import com.trading.tcg.global.exception.ErrorCode

enum class UserErrorCode(
    override val statusCode: Int,
    override val errorCode: String,
    override val errorMessage: String
) : ErrorCode {
    NOT_FOUND_USER(404, "NOT_FOUND_USER", "유저가 존재하지 않습니다."),
    BLANK_EMAIL(400, "BLANK_EMAIL", "이메일이 입력되지 않았습니다."),
    OUT_OF_RANGE_EMAIL(400, "OUT_OF_RANGE_EMAIL", "이메일이 글자수 범위를 벗어났습니다."),
    INCORRECT_EMAIL(409, "INCORRECT_EMAIL", "이메일 형식이 올바르지 않습니다."),
    DUPLICATED_EMAIL(409, "DUPLICATED_EMAIL", "중복된 이메일입니다."),
    BLANK_PASSWORD(400, "BLANK_PASSWORD", "비밀번호가 입력되지 않았습니다."),
    OUT_OF_RANGE_PASSWORD(400, "OUT_OF_RANGE_PASSWORD", "비밀번호가 글자수 범위를 벗어났습니다."),
    INCORRECT_PASSWORD(409, "INCORRECT_PASSWORD", "비밀번호 형식이 올바르지 않습니다."),
    NOT_MATCH_USER(400, "NOT_MATCH_USER", "아이디 혹은 비밀번호가 일치하지 않습니다.");
}
