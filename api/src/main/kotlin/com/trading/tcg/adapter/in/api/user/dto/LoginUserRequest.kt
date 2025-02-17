package com.trading.tcg.adapter.`in`.api.user.dto

import com.trading.tcg.application.user.dto.request.LoginUserCommand
import com.trading.tcg.global.exception.CustomException
import com.trading.tcg.global.exception.RequestErrorCode
import com.trading.tcg.user.domain.User
import com.trading.tcg.user.exception.UserErrorCode
import java.util.regex.Pattern

data class LoginUserRequest(
    val email: String,
    val password: String
) {
    fun toCommand(): LoginUserCommand {
        if (email.isEmpty())
            throw CustomException(RequestErrorCode.MISSING_REQUIRED_PARAMETER)
        if (password.isEmpty())
            throw CustomException(RequestErrorCode.MISSING_REQUIRED_PARAMETER)
        if (!Pattern.matches(User.emailFormat, email))
            throw CustomException(UserErrorCode.INCORRECT_EMAIL_FORMAT)
        if (!Pattern.matches(User.passwordFormat, password))
            throw CustomException(UserErrorCode.INCORRECT_PASSWORD_FORMAT)
        if (User.emailMinLength > email.length || User.emailMaxLength < email.length)
            throw CustomException(UserErrorCode.OUT_OF_RANGE_EMAIL)
        if (User.passwordMinLength > password.length || User.passwordMaxLength < password.length)
            throw CustomException(UserErrorCode.OUT_OF_RANGE_PASSWORD)

        return LoginUserCommand(
            email = email,
            password = password
        )
    }
}
