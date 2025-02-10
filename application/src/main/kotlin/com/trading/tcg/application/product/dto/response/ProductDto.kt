package com.trading.tcg.application.product.dto.response

import com.trading.tcg.global.util.toDisplayString
import java.math.BigDecimal
import java.time.LocalDateTime

data class ProductDto(
    val id: Long?,
    val name: String,
    val image: String,
    val recentDealPrice: String,
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
        recentDealPrice: BigDecimal?,
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
        id = id,
        name = name,
        image = image,
        recentDealPrice = recentDealPrice?.toDisplayString() ?: "-",
        directBuyPrice = directBuyPrice?.toDisplayString() ?: "-",
        directSellPrice = directSellPrice?.toDisplayString() ?: "-",
        dealCount = dealCount,
        bidCount = buyBidCount + sellBidCount,
        bookmarkCount = bookmarkCount,
        isBookmarked = isBookmarked,
        createdTime = createdTime,
        updatedTime = updatedTime
    )
}
