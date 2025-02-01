package com.trading.tcg.application.user.domain

import java.time.LocalDateTime

class User(
    val id: Long? = null,
    val email: String,
    val password: String,
    val role: UserRole,
    val createdTime: LocalDateTime? = null,
    val updatedTime: LocalDateTime? = null
)
