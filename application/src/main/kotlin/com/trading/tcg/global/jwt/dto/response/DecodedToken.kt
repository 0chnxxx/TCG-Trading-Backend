package com.trading.tcg.global.jwt.dto.response

import com.trading.tcg.application.user.domain.UserProvider


data class DecodedToken(
    val email: String,
    val provider: UserProvider
)
