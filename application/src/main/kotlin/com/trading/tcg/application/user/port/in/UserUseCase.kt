package com.trading.tcg.application.user.port.`in`

import com.trading.tcg.application.user.dto.request.LoginUserCommand
import com.trading.tcg.application.user.dto.request.RegisterUserCommand
import com.trading.tcg.global.dto.Response
import com.trading.tcg.global.jwt.dto.response.JwtToken

interface UserUseCase {
    fun login(command: LoginUserCommand): Response<JwtToken>
    fun register(command: RegisterUserCommand): Response<JwtToken>
}
