package com.trading.tcg.user.dto.request

import com.trading.tcg.user.domain.User
import com.trading.tcg.user.domain.UserRole
import java.time.LocalDateTime

data class RegisterUserCommand(
    val email: String,
    val password: String
)
