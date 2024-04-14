package com.trading.tcg.user.dto.request

data class LoginUserCommand(
    val email: String,
    val password: String
)
