package com.trading.tcg.application.product.dto.response

data class ProductBidTrendDto(
    val prices: List<Long>,
    val minPrice: Long,
    val maxPrice: Long
)
