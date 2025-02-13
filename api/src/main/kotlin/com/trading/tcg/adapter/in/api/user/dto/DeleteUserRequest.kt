package com.trading.tcg.adapter.`in`.api.user.dto

import com.trading.tcg.application.user.dto.request.DeleteUserCommand
import com.trading.tcg.application.user.dto.request.LoginUserCommand
import com.trading.tcg.global.validation.SelfValidator
import com.trading.tcg.global.validation.ValidationGroup
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Pattern
import org.hibernate.validator.constraints.Length
import org.springframework.web.bind.annotation.PathVariable

data class DeleteUserRequest(
    @PathVariable
    val userId: Long?
) {
    fun toCommand(): DeleteUserCommand {
        return DeleteUserCommand(
            userId = userId ?: 0
        )
    }
}
