package com.trading.tcg.application.user.dto.request

data class LoginUserCommand(
    val email: String,
    val password: String
)
