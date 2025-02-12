package com.trading.tcg.application.product.dto.request

data class FindProductBidTrendQuery(
    val userId: Long,
    val productId: Long
)
