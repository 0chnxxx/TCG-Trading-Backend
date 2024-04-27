package com.trading.tcg.adapter.`in`.api.user.dto

import com.trading.tcg.application.user.dto.request.LoginUserCommand
import com.trading.tcg.global.validation.ValidationGroup
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import org.hibernate.validator.constraints.Length

data class LoginUserRequest(
    @field:NotBlank(message = "이메일이 입력되지 않았습니다.", groups = [ValidationGroup.First::class])
    @field:Email(message = "이메일 형식이 올바르지 않습니다.", groups = [ValidationGroup.Second::class])
    @field:Length(min = 5, max = 50, message = "이메일이 글자수 범위를 벗어났습니다.")
    val email: String,

    @field:NotBlank(message = "비밀번호가 입력되지 않았습니다.", groups = [ValidationGroup.First::class])
    @field:Pattern(
        regexp = "^(?=.*[a-zA-Z])(?=.*[!@#\$%^*+=-])(?=.*[0-9]).{8,15}\$",
        message = "비밀번호 형식이 올바르지 않습니다.",
        groups = [ValidationGroup.Second::class]
    )
    @field:Length(min = 8, max = 20, message = "비밀번호가 글자수 범위를 벗어났습니다.")
    val password: String
) {
    fun toCommand(): LoginUserCommand {
        return LoginUserCommand(
            email = email,
            password = password
        )
    }
}
