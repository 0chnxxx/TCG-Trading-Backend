package com.trading.tcg.application.product.dto.response

import java.math.BigDecimal
import java.time.LocalDateTime

data class ProductDto(
    val id: Long?,
    val name: String,
    val image: String,
    val recentPrice: String,
    val directBuyPrice: String,
    val directSellPrice: String,
    val dealCount: Int,
    val bidCount: Int,
    val bookmarkCount: Int,
    val isBookmarked: Boolean,
    val createdTime: LocalDateTime,
    val updatedTime: LocalDateTime?
) {
    constructor(
        id: Long?,
        name: String,
        image: String,
        recentPrice: BigDecimal?,
        directBuyPrice: BigDecimal?,
        directSellPrice: BigDecimal?,
        dealCount: Int,
        buyBidCount: Int,
        sellBidCount: Int,
        bookmarkCount: Int,
        isBookmarked: Boolean,
        createdTime: LocalDateTime,
        updatedTime: LocalDateTime?
    ): this(
        id,
        name,
        image,
        recentPrice?.toPlainString() ?: "-",
        directBuyPrice?.toPlainString() ?: "-",
        directSellPrice?.toPlainString() ?: "-",
        dealCount,
        buyBidCount + sellBidCount,
        bookmarkCount,
        isBookmarked,
        createdTime,
        updatedTime
    )
}
