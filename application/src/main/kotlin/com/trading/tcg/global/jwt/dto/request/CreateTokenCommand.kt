package com.trading.tcg.global.jwt.dto.request

import com.trading.tcg.user.domain.User

data class CreateTokenCommand(
    val user: User
)
