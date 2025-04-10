package com.trading.tcg.application.product.dto.response

import com.trading.tcg.global.util.BigDecimalUtil.toDisplayString
import java.math.BigDecimal
import java.time.LocalDateTime

data class ProductDto(
    val id: Long,
    val name: String,
    val image: String,
    val recentDealPrice: String,
    val dealCount: Long,
    val bidCount: Long,
    val bookmarkCount: Long,
    val isBookmarked: Boolean,
    val createdTime: LocalDateTime,
    val updatedTime: LocalDateTime?
) {
    constructor(
        id: Long,
        name: String,
        image: String,
        recentDealPrice: BigDecimal?,
        dealCount: Long,
        buyBidCount: Long,
        sellBidCount: Long,
        bookmarkCount: Long,
        isBookmarked: Boolean,
        createdTime: LocalDateTime,
        updatedTime: LocalDateTime?
    ): this(
        id = id,
        name = name,
        image = image,
        recentDealPrice = recentDealPrice?.toDisplayString() ?: "-",
        dealCount = dealCount,
        bidCount = buyBidCount + sellBidCount,
        bookmarkCount = bookmarkCount,
        isBookmarked = isBookmarked,
        createdTime = createdTime,
        updatedTime = updatedTime
    )
}
