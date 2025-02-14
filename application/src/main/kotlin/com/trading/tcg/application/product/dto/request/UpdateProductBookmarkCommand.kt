package com.trading.tcg.application.product.dto.request

data class UpdateProductBookmarkCommand(
    val userId: Long,
    val productId: Long
)
