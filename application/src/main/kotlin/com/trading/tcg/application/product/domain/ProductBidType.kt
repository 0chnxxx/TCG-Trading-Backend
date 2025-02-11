package com.trading.tcg.application.product.domain

import com.trading.tcg.global.exception.CustomException
import com.trading.tcg.global.exception.ServiceErrorCode

enum class ProductBidType(
    val queryName: String,
    val displayName: String
) {
    BUY("BUY", "구매"),
    SELL("SELL", "판매");

    companion object {
        fun ofQuery(type: String): ProductBidType {
            return ProductBidType.entries.find { it.queryName.uppercase() == type.uppercase() }
                ?: throw CustomException(ServiceErrorCode.INVALID_PRODUCT_BID_TYPE)
        }
    }
}
