package com.trading.tcg.adapter.`in`.api.product.dto

import com.trading.tcg.application.product.dto.request.FindProductBidTrendQuery
import com.trading.tcg.application.product.dto.request.FindProductBidsQuery
import com.trading.tcg.global.dto.Provider
import com.trading.tcg.global.validation.SelfValidator
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import org.springframework.web.bind.annotation.PathVariable

data class FindProductBidTrendRequest(
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
