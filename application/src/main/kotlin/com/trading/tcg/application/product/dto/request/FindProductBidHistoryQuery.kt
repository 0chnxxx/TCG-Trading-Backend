package com.trading.tcg.application.product.dto.request

import com.trading.tcg.global.dto.SortBy
import com.trading.tcg.application.product.dto.common.ProductBidField
import com.trading.tcg.product.domain.ProductBidStatus
import com.trading.tcg.product.domain.ProductBidType

data class FindProductBidHistoryQuery(
    val userId: Long,
    val productId: Long,
    val order: ProductBidField,
    val sort: SortBy,
    val type: ProductBidType? = null,
    val status: ProductBidStatus? = null,
    val page: Int,
    val size: Int
)
