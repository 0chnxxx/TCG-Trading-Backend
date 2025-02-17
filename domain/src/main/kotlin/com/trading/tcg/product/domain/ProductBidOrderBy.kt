package com.trading.tcg.product.domain

import com.trading.tcg.global.exception.CustomException
import com.trading.tcg.product.exception.ProductErrorCode

enum class ProductBidOrderBy(
    val queryName: String,
    val displayName: String
) {
    CREATED_TIME("bidPlacedTime", "입찰 시간"),
    QUANTITY("quantity", "수량"),
    PRICE("price", "가격");

    companion object {
        fun ofQuery(queryName: String): ProductBidOrderBy {
            return ProductBidOrderBy.entries.find { it.queryName.uppercase() == queryName.uppercase() }
                ?: throw CustomException(ProductErrorCode.INVALID_PRODUCT_BID_ORDER)
        }
    }
}
