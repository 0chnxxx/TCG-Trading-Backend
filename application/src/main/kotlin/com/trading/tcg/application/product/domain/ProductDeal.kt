package com.trading.tcg.application.product.domain

import java.math.BigDecimal
import java.time.LocalDateTime

class ProductDeal(
    val id: Long?,
    val product: Product,
    val buyBid: ProductBid,
    val sellBid: ProductBid,
    val price: BigDecimal,
    val quantity: Int,
    val createdTime: LocalDateTime,
    val updatedTime: LocalDateTime
) {
}
