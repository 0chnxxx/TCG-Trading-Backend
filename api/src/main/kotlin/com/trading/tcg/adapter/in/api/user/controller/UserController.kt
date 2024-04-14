package com.trading.tcg.adapter.`in`.api.user.controller

import com.trading.tcg.adapter.`in`.api.user.dto.LoginUserRequest
import com.trading.tcg.adapter.`in`.api.user.dto.RegisterUserRequest
import com.trading.tcg.global.jwt.dto.response.JwtToken
import com.trading.tcg.global.dto.Response
import com.trading.tcg.global.validation.ValidationSequence
import com.trading.tcg.user.port.`in`.LoginUserUseCase
import com.trading.tcg.user.port.`in`.RegisterUserUseCase
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@Tag(name = "유저")
@RestController
class UserController(
    private val registerUserUseCase: RegisterUserUseCase,
    private val loginUserUseCase: LoginUserUseCase
) {
    @Operation(summary = "유저 회원가입", description = "유저를 가입시킨다.")
    @PostMapping("/users/register")
    fun registerUser(
        @RequestBody @Validated(ValidationSequence::class) request: RegisterUserRequest
    ): ResponseEntity<Response<JwtToken>> {
        return ResponseEntity(registerUserUseCase.register(request.toCommand()), HttpStatus.CREATED)
    }

    @Operation(summary = "유저 로그인", description = "유저를 로그인시킨다.")
    @PostMapping("/users/login")
    fun loginUser(
        @RequestBody @Validated(ValidationSequence::class) request: LoginUserRequest
    ): ResponseEntity<Response<JwtToken>> {
        return ResponseEntity(loginUserUseCase.login(request.toCommand()), HttpStatus.OK)
    }
}
