package com.trading.tcg.adapter.`in`.api.user.dto

import com.trading.tcg.application.user.dto.request.DeleteUserCommand
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
