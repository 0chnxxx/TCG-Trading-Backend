package com.trading.tcg.adapter.`in`.api.product.dto

import com.trading.tcg.application.product.dto.request.UpdateProductBookmarkCommand
import com.trading.tcg.global.dto.Provider
import org.springframework.web.bind.annotation.PathVariable

data class UpdateProductBookmarkRequest(
    @PathVariable("productId")
    val productId: Long?
) {
    fun toCommand(provider: Provider): UpdateProductBookmarkCommand {
        return UpdateProductBookmarkCommand(
            userId = provider.getUser()?.id ?: 0,
            productId = productId ?: 0
        )
    }
}
