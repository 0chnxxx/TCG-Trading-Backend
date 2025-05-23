package com.trading.tcg.adapter.`in`.api.product.dto

import com.trading.tcg.application.product.dto.request.FindProductBidHistoryQuery
import com.trading.tcg.global.dto.SortBy
import com.trading.tcg.global.dto.Provider
import com.trading.tcg.global.exception.CustomException
import com.trading.tcg.global.exception.RequestErrorCode
import com.trading.tcg.application.product.dto.common.ProductBidField
import com.trading.tcg.product.domain.ProductBidStatus
import com.trading.tcg.product.domain.ProductBidType
import org.springframework.web.bind.annotation.PathVariable

data class FindProductBidHistoryRequest(
    @PathVariable
    val productId: Long,
    val type: String?,
    val status: String?,
    val page: Int?,
    val size: Int?
) {
    fun toQuery(provider: Provider): FindProductBidHistoryQuery {
        return FindProductBidHistoryQuery(
            userId = provider.getUser()?.id ?: 0,
            productId = productId,
            order = ProductBidField.CREATED_TIME,
            sort = SortBy.DESC,
            type = type?.let { ProductBidType.ofQuery(it) },
            status = status?.let { ProductBidStatus.ofQuery(it) },
            page = page?.also { if (it < 1) throw CustomException(RequestErrorCode.OUT_OF_PAGE) } ?: 1,
            size = size?.also { if (it < 1) throw CustomException(RequestErrorCode.OUT_OF_SIZE) } ?: 10
        )
    }
}
