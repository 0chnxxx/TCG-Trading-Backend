package com.trading.tcg.adapter.`in`.swagger.user

import com.trading.tcg.adapter.`in`.api.user.dto.LoginUserRequest
import com.trading.tcg.adapter.`in`.api.user.dto.RegisterUserRequest
import com.trading.tcg.global.dto.Response
import com.trading.tcg.global.jwt.dto.response.JwtToken
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity

@Tag(name = "유저")
interface UserSwagger {
    @Operation(summary = "유저 회원가입", description = "유저를 가입시킨다.")
    fun registerUser(
        request: RegisterUserRequest
    ): ResponseEntity<Response<JwtToken>>

    @Operation(summary = "유저 로그인", description = "유저를 로그인시킨다.")
    fun loginUser(
        request: LoginUserRequest
    ): ResponseEntity<Response<JwtToken>>
}
