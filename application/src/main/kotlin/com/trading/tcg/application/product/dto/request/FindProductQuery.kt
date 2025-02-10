package com.trading.tcg.application.product.dto.request

data class FindProductQuery(
    val userId: Long,
    val productId: Long
)
