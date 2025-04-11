package com.trading.tcg.adapter.`in`.api.product.dto

import com.trading.tcg.application.product.dto.request.PlaceProductSellBidCommand
import com.trading.tcg.global.dto.Provider
import org.springframework.web.bind.annotation.PathVariable
import java.time.LocalDate

data class PlaceProductSellBidRequest(
    val price: Long,
    val quantity: Int,
    val dueDateOffset: Long
) {
    fun toCommand(provider: Provider, productId: Long): PlaceProductSellBidCommand {
        return PlaceProductSellBidCommand(
            userId = provider.getUser()?.id ?: 0,
            productId = productId,
            price = price,
            quantity = quantity,
            dueDate = LocalDate.now().plusDays(dueDateOffset)
        )
    }
}
