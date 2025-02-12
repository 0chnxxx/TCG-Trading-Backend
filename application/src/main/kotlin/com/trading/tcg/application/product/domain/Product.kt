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
    val deals: List<ProductDeal>,
    val buyBids: List<ProductBid>,
    val sellBids: List<ProductBid>,
    val bookmarks: List<ProductBookmark>,
    val createdTime: LocalDateTime,
    val updatedTime: LocalDateTime?
)
