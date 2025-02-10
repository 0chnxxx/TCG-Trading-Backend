package com.trading.tcg.application.product.domain

import com.trading.tcg.application.card.domain.Card
import java.math.BigDecimal
import java.time.LocalDateTime

class Product(
    val id: Long?,
    val card: Card,
    val recentDealPrice: BigDecimal?,
    val directBuyPrice: BigDecimal?,
    val directSellPrice: BigDecimal?,
    val dealCount: Int,
    val buyBidCount: Int,
    val sellBidCount: Int,
    val bookmarkCount: Int,
    val createdTime: LocalDateTime,
    val updatedTime: LocalDateTime?
)
