package com.trading.tcg.application.user.port.`in`

import com.trading.tcg.application.user.dto.request.DeleteUserCommand
import com.trading.tcg.application.user.dto.request.FindUserQuery
import com.trading.tcg.application.user.dto.request.LoginUserCommand
import com.trading.tcg.application.user.dto.request.RegisterUserCommand
import com.trading.tcg.application.user.dto.response.UserDto
import com.trading.tcg.global.jwt.dto.response.JwtToken

interface UserUseCase {
    fun findMe(query: FindUserQuery): UserDto
    fun login(command: LoginUserCommand): JwtToken
    fun register(command: RegisterUserCommand): JwtToken
    fun delete(command: DeleteUserCommand)
}
