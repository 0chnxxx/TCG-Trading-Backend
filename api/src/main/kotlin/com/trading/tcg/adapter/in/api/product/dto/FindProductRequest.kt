package com.trading.tcg.adapter.`in`.api.product.dto

import com.trading.tcg.application.product.dto.request.FindProductQuery
import com.trading.tcg.global.dto.Provider
import org.springframework.web.bind.annotation.PathVariable

data class FindProductRequest(
    @PathVariable("productId")
    val productId: Long?
) {
    fun toQuery(provider: Provider): FindProductQuery {
        return FindProductQuery(
            userId = provider.getUser()?.id ?: 0,
            productId = productId ?: 0
        )
    }
}
