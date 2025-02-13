package com.trading.tcg.adapter.`in`.swagger.user

import com.trading.tcg.adapter.`in`.api.user.dto.DeleteUserRequest
import com.trading.tcg.adapter.`in`.api.user.dto.LoginUserRequest
import com.trading.tcg.adapter.`in`.api.user.dto.RegisterUserRequest
import com.trading.tcg.application.user.dto.response.UserDto
import com.trading.tcg.global.dto.Provider
import com.trading.tcg.global.dto.Response
import com.trading.tcg.global.jwt.dto.response.JwtToken
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity

@Tag(name = "유저")
interface UserSwagger {
    @Operation(summary = "내 정보 조회", description = "JWT 토큰으로 내 정보를 조회한다.")
    fun findMe(
        provider: Provider
    ): ResponseEntity<Response<UserDto>>

    @Operation(summary = "유저 회원가입", description = "유저를 가입시킨다.")
    fun registerUser(
        request: RegisterUserRequest
    ): ResponseEntity<Response<JwtToken>>

    @Operation(summary = "유저 로그인", description = "유저를 로그인시킨다.")
    fun loginUser(
        request: LoginUserRequest
    ): ResponseEntity<Response<JwtToken>>

    @Operation(summary = "유저 회원탈퇴", description = "유저를 탈퇴시킨다.")
    fun deleteUser(
        request: DeleteUserRequest
    ): ResponseEntity<Unit>
}
