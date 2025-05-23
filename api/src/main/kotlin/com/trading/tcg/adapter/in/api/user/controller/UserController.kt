package com.trading.tcg.adapter.`in`.api.user.controller

import com.trading.tcg.adapter.`in`.api.user.dto.DeleteUserRequest
import com.trading.tcg.adapter.`in`.api.user.dto.LoginUserRequest
import com.trading.tcg.adapter.`in`.api.user.dto.RegisterUserRequest
import com.trading.tcg.adapter.`in`.swagger.user.UserSwagger
import com.trading.tcg.application.user.dto.request.FindUserQuery
import com.trading.tcg.application.user.dto.response.UserDto
import com.trading.tcg.application.user.port.`in`.UserUseCase
import com.trading.tcg.global.dto.Provider
import com.trading.tcg.global.dto.Response
import com.trading.tcg.global.jwt.dto.response.JwtToken
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val userUseCase: UserUseCase
): UserSwagger {
    @GetMapping("/users/me")
    @PreAuthorize("authentication.principal.user.id != null")
    override fun findMe(
        @AuthenticationPrincipal
        provider: Provider
    ): ResponseEntity<Response<UserDto>> {
        val query = FindUserQuery(
            userId = provider.getUser()?.id ?: 0
        )
        val user = userUseCase.findMe(query)

        val response = Response.of(
            data = user
        )


        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @PostMapping("/users/register")
    override fun registerUser(
        @RequestBody
        request: RegisterUserRequest
    ): ResponseEntity<Response<JwtToken>> {
        val token = userUseCase.register(request.toCommand())

        val response = Response.of(
            data = token
        )

        return ResponseEntity(response, HttpStatus.CREATED)
    }

    @PostMapping("/users/login")
    override fun loginUser(
        @RequestBody
        request: LoginUserRequest
    ): ResponseEntity<Response<JwtToken>> {
        val token = userUseCase.login(request.toCommand())

        val response = Response.of(
            data = token
        )

        return ResponseEntity(response, HttpStatus.OK)
    }

    @DeleteMapping("/users/{userId}")
    @PreAuthorize("authentication.principal.user.id == #userId")
    override fun deleteUser(
        request: DeleteUserRequest
    ): ResponseEntity<Unit> {
        userUseCase.delete(request.toCommand())
        return ResponseEntity(HttpStatus.OK)
    }
}
