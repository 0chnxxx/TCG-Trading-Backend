package com.trading.tcg.global.jwt.dto.response

data class JwtToken(
    val accessToken: String,
    val refreshToken: String
)
