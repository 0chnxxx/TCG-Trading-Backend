package com.trading.tcg.application.product.dto.response

import com.trading.tcg.global.util.toDisplayString
import java.math.BigDecimal
import java.time.LocalDateTime

data class ProductBidDto(
    val price: String,
    val quantity: Int,
    val date: LocalDateTime,
    val isMine: Boolean
) {
    constructor(
        price: BigDecimal,
        quantity: Int,
        date: LocalDateTime,
        isMine: Boolean
    ): this(
        price = price.toDisplayString(),
        quantity = quantity,
        date = date,
        isMine = isMine
    )
}
