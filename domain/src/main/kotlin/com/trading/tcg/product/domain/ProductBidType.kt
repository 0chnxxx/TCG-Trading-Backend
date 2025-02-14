package com.trading.tcg.product.domain

import com.trading.tcg.global.exception.CustomException
import com.trading.tcg.product.exception.ProductErrorCode

enum class ProductBidType(
    val queryName: String,
    val displayName: String
) {
    BUY("buy", "구매"),
    SELL("sell", "판매"),
    DEAL("deal", "체결");

    companion object {
        fun ofQuery(queryName: String): ProductBidType {
            return ProductBidType.entries.find { it.queryName.uppercase() == queryName.uppercase() }
                ?: throw CustomException(ProductErrorCode.INVALID_PRODUCT_BID_TYPE)
        }
    }
}
