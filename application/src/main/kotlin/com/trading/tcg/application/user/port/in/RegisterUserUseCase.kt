package com.trading.tcg.application.user.port.`in`

import com.trading.tcg.application.user.dto.request.RegisterUserCommand
import com.trading.tcg.global.dto.Response
import com.trading.tcg.global.jwt.dto.response.JwtToken

interface RegisterUserUseCase {
    fun register(command: RegisterUserCommand): Response<JwtToken>
}
