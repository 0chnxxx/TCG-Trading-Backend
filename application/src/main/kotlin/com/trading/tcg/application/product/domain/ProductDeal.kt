package com.trading.tcg.application.product.domain

import com.trading.tcg.application.user.domain.User
import java.math.BigDecimal
import java.time.LocalDateTime

class ProductDeal(
    val id: Long?,
    val product: Product,
    val buyer: User,
    val seller: User,
    val type: ProductDealType,
    val price: BigDecimal,
    val quantity: Int,
    val createdTime: LocalDateTime,
    val updatedTime: LocalDateTime
) {
}
