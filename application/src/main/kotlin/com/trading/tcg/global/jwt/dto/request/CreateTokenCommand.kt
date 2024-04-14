package com.trading.tcg.global.jwt.dto.request

import com.trading.tcg.user.domain.User
import com.trading.tcg.user.domain.UserProvider

data class CreateTokenCommand(
    val user: User,
    val provider: UserProvider
)
