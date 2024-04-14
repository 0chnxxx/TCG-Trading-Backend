package com.trading.tcg.user.port.`in`

import com.trading.tcg.global.jwt.dto.response.JwtToken
import com.trading.tcg.global.dto.Response
import com.trading.tcg.user.dto.request.RegisterUserCommand

interface RegisterUserUseCase {
    fun register(command: RegisterUserCommand): Response<JwtToken>
}
