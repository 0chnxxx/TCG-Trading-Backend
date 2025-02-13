package com.trading.tcg.adapter.`in`.api.user.controller

import com.trading.tcg.adapter.`in`.api.user.dto.LoginUserRequest
import com.trading.tcg.adapter.`in`.api.user.dto.RegisterUserRequest
import com.trading.tcg.adapter.`in`.swagger.user.UserSwagger
import com.trading.tcg.application.user.port.`in`.UserUseCase
import com.trading.tcg.global.dto.Response
import com.trading.tcg.global.jwt.dto.response.JwtToken
import com.trading.tcg.global.validation.ValidationSequence
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userUseCase: UserUseCase
): UserSwagger {
    @PostMapping("/users/register")
    override fun registerUser(
        @RequestBody
        request: RegisterUserRequest
    ): ResponseEntity<Response<JwtToken>> {
        return ResponseEntity(userUseCase.register(request.toCommand()), HttpStatus.CREATED)
    }

    @PostMapping("/users/login")
    override fun loginUser(
        @RequestBody
        request: LoginUserRequest
    ): ResponseEntity<Response<JwtToken>> {
        return ResponseEntity(userUseCase.login(request.toCommand()), HttpStatus.OK)
    }
}
