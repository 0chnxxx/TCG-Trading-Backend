package com.trading.tcg.adapter.`in`.api.product.dto

import com.trading.tcg.application.product.dto.request.FindProductBidHistoryQuery
import com.trading.tcg.global.domain.SortBy
import com.trading.tcg.global.dto.Provider
import com.trading.tcg.global.validation.SelfValidator
import com.trading.tcg.product.domain.ProductBidOrderBy
import com.trading.tcg.product.domain.ProductBidStatus
import com.trading.tcg.product.domain.ProductBidType
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import org.springframework.web.bind.annotation.PathVariable

data class FindProductBidHistoryRequest(
    @PathVariable
    val productId: Long,

    @field:NotNull(message = "필수 파라미터가 입력되지 않았습니다.")
    val type: String?,

    @field:Min(value = 1, message = "페이지 번호가 범위를 벗어났습니다.")
    val page: Int?,

    @field:Min(value = 1, message = "페이지 크기가 범위를 벗어났습니다.")
    val size: Int?
): SelfValidator() {
    fun toQuery(provider: Provider): FindProductBidHistoryQuery {
        validate()

        return FindProductBidHistoryQuery(
            userId = provider.getUser()?.id ?: 0,
            productId = productId,
            order = ProductBidOrderBy.CREATED_TIME,
            sort = SortBy.DESC,
            type = ProductBidType.ofQuery(type!!),
            status = ProductBidStatus.BIDDING,
            page = page ?: 1,
            size = size ?: 10
        )
    }
}
