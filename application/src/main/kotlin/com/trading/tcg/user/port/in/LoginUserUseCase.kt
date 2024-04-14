package com.trading.tcg.user.port.`in`

import com.trading.tcg.global.jwt.dto.response.JwtToken
import com.trading.tcg.global.dto.Response
import com.trading.tcg.user.dto.request.LoginUserCommand

interface LoginUserUseCase {
    fun login(command: LoginUserCommand): Response<JwtToken>
}
