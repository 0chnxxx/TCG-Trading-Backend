package com.trading.tcg.application.product.dto.request

data class FindProductBidsQuery(
    val userId: Long,
    val productId: Long,
    val order: String,
    val sort: String,
    val type: String,
    val status: String?,
    val page: Int,
    val size: Int
)
