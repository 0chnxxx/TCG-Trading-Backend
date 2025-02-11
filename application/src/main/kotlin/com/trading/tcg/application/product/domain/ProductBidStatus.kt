package com.trading.tcg.application.product.domain

import com.trading.tcg.global.exception.CustomException
import com.trading.tcg.global.exception.ServiceErrorCode

enum class ProductBidStatus(
    val queryName: String,
    val displayName: String
) {
    BIDDING("BIDDING", "입찰"),
    DEALT("DEALT", "체결"),
    CANCELLED("CANCELLED", "취소"),
    CLOSED("CLOSED", "종료");

    companion object {
        fun ofQuery(status: String): ProductBidStatus {
            return ProductBidStatus.entries.find { it.queryName.uppercase() == status.uppercase() }
                ?: throw CustomException(ServiceErrorCode.INVALID_PRODUCT_BID_STATUS)
        }
    }
}
