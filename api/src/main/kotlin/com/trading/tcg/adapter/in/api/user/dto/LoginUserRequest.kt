package com.trading.tcg.adapter.`in`.api.user.dto

import com.trading.tcg.user.dto.request.LoginUserCommand

data class LoginUserRequest(
    val email: String,
    val password: String
) {
    fun toCommand(): LoginUserCommand {
        return LoginUserCommand(
            email = email,
            password = password
        )
    }
}
