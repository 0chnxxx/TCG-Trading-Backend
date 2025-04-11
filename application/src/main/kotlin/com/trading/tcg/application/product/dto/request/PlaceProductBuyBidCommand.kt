package com.trading.tcg.application.product.dto.request

import java.time.LocalDate

data class PlaceProductBuyBidCommand(
    val userId: Long,
    val productId: Long,
    val price: Long,
    val quantity: Int,
    val dueDate: LocalDate
)
