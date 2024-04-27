package com.trading.tcg.global.jwt.dto.request

import com.trading.tcg.application.user.domain.User
import com.trading.tcg.application.user.domain.UserProvider

data class CreateTokenCommand(
    val user: User,
    val provider: UserProvider
)
