package com.trading.tcg.adapter.`in`.api.product.dto

import com.trading.tcg.application.product.dto.request.FindProductBidsQuery
import com.trading.tcg.global.dto.Provider
import com.trading.tcg.global.validation.SelfValidator
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.Pattern
import org.springframework.web.bind.annotation.PathVariable

data class FindProductBidsRequest(
    @PathVariable
    val productId: Long,

    @field:Pattern(regexp = "^(createdTime|price|quantity)", message = "유효한 정렬 대상이 아닙니다.")
    val order: String?,

    @field:Pattern(regexp = "^(asc|desc)", message = "유효한 정렬 방식이 아닙니다.")
    val sort: String?,

    @field:Pattern(regexp = "^(buy|sell)", message = "유효한 타입이 아닙니다.")
    val type: String?,

    @field:Pattern(regexp = "^(bidding|dealt|cancelled|closed)", message = "유효한 상태가 아닙니다.")
    val status: String?,

    @field:Min(value = 1, message = "페이지 번호가 범위를 벗어났습니다.")
    val page: Int?,

    @field:Min(value = 1, message = "페이지 크기가 범위를 벗어났습니다.")
    val size: Int?
): SelfValidator() {
    fun toQuery(provider: Provider): FindProductBidsQuery {
        validate()

        return FindProductBidsQuery(
            userId = provider.getUser()?.id ?: 0,
            productId = productId,
            order = order ?: "createdTime",
            sort = sort ?: "desc",
            type = type,
            status = status,
            page = page ?: 1,
            size = size ?: 10
        )
    }
}
