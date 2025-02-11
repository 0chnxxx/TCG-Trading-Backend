package com.trading.tcg.application.product.dto.response

import com.trading.tcg.application.product.domain.ProductBidStatus
import com.trading.tcg.application.product.domain.ProductBidType
import com.trading.tcg.global.util.toDisplayString
import java.math.BigDecimal
import java.time.LocalDateTime

data class ProductBidDto(
    val type: String,
    val status: String,
    val price: String,
    val totalQuantity: Int,
    val remainingQuantity: Int,
    val createdTime: LocalDateTime,
    val closedTime: LocalDateTime,
    val isMine: Boolean
) {
    constructor(
        type: ProductBidType,
        status: ProductBidStatus,
        price: BigDecimal,
        totalQuantity: Int,
        remainingQuantity: Int,
        createdTime: LocalDateTime,
        closedTime: LocalDateTime,
        isMine: Boolean
    ): this(
        type = type.displayName,
        status = status.displayName,
        price = price.toDisplayString(),
        totalQuantity = totalQuantity,
        remainingQuantity = remainingQuantity,
        createdTime = createdTime,
        closedTime = closedTime,
        isMine = isMine
    )
}
