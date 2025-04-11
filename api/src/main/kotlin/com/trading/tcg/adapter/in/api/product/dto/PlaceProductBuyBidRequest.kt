package com.trading.tcg.adapter.`in`.api.product.dto

import com.trading.tcg.application.product.dto.request.PlaceProductBuyBidCommand
import com.trading.tcg.global.dto.Provider
import java.time.LocalDate

data class PlaceProductBuyBidRequest(
    val price: Long,
    val quantity: Int,
    val dueDateOffset: Long
) {
    fun toCommand(provider: Provider, productId: Long): PlaceProductBuyBidCommand {
        return PlaceProductBuyBidCommand(
            userId = provider.getUser()?.id ?: 0,
            productId = productId,
            price = price,
            quantity = quantity,
            dueDate = LocalDate.now().plusDays(dueDateOffset)
        )
    }
}
