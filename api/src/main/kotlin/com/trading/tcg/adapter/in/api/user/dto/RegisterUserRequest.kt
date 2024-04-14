package com.trading.tcg.adapter.`in`.api.user.dto

import com.trading.tcg.user.dto.request.RegisterUserCommand

data class RegisterUserRequest(
    val email: String,
    val password: String
) {
    fun toCommand(): RegisterUserCommand {
        return RegisterUserCommand(
            email = email,
            password = password
        )
    }
}
