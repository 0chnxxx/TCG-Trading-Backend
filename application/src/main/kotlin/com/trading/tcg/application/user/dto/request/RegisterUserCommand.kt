package com.trading.tcg.application.user.dto.request

data class RegisterUserCommand(
    val email: String,
    val password: String
)
