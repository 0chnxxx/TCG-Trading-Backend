package com.trading.tcg.user.exception

import com.trading.tcg.global.exception.ErrorCode

enum class UserErrorCode(
    private val statusCode: Int,
    private val errorMessage: String
) : ErrorCode {
    BLANK_EMAIL(400, "이메일이 입력되지 않았습니다."),
    OUT_OF_RANGE_EMAIL(400, "이메일이 글자수 범위를 벗어났습니다."),
    INCORRECT_EMAIL(409, "이메일 형식이 올바르지 않습니다."),
    DUPLICATED_EMAIL(409, "중복된 이메일입니다."),
    BLANK_PASSWORD(400, "비밀번호가 입력되지 않았습니다."),
    OUT_OF_RANGE_PASSWORD(400, "비밀번호가 글자수 범위를 벗어났습니다."),
    INCORRECT_PASSWORD(409, "비밀번호 형식이 올바르지 않습니다."),
    NOT_MATCH_USER(400, "아이디 혹은 비밀번호가 일치하지 않습니다.");

    override fun getStatusCode() = statusCode
    override fun getErrorCode() = this.name
    override fun getErrorMessage() = errorMessage
}
