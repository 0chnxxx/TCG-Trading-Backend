package com.trading.tcg.product.domain

import com.trading.tcg.global.exception.CustomException
import com.trading.tcg.product.exception.ProductErrorCode

enum class ProductOrderBy(
    val queryName: String,
    val displayName: String
) {
    BID_PLACED_TIME("bidPlacedTime", "입찰 시간"),
    BID_CLOSED_TIME("bidClosedTime", "입찰 종료 시간"),
    BID_COUNT("bidCount", "입찰 수"),
    DEAL_COUNT("dealCount", "거래 수"),
    PRICE("price", "가격");

    companion object {
        fun ofQuery(queryName: String): ProductOrderBy {
            return ProductOrderBy.entries.find { it.queryName.uppercase() == queryName.uppercase() }
                ?: throw CustomException(ProductErrorCode.INVALID_PRODUCT_ORDER)
        }
    }
}
