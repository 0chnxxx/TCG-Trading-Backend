package com.trading.tcg.application.product.domain

import com.trading.tcg.application.user.domain.User
import java.math.BigDecimal
import java.time.LocalDateTime

class ProductBid(
    val id: Long?,
    val product: Product,
    val user: User,
    val type: ProductBidType,
    val status: ProductBidStatus,
    val price: BigDecimal,
    val totalQuantity: Int,
    val remainingQuantity: Int,
    val closedTime: LocalDateTime,
    val createdTime: LocalDateTime,
    val updatedTime: LocalDateTime
) {
}
