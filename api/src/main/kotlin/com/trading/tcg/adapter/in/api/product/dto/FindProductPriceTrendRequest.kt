package com.trading.tcg.adapter.`in`.api.product.dto

import com.trading.tcg.application.product.dto.request.FindProductBidTrendQuery
import com.trading.tcg.global.dto.Provider
import com.trading.tcg.global.validation.SelfValidator
import org.springframework.web.bind.annotation.PathVariable

data class FindProductPriceTrendRequest(
    @PathVariable
    val productId: Long
): SelfValidator() {
    fun toQuery(provider: Provider): FindProductBidTrendQuery {
        validate()

        return FindProductBidTrendQuery(
            userId = provider.getUser()?.id ?: 0,
            productId = productId
        )
    }
}
