package com.trading.tcg.application.product.port.out

import com.trading.tcg.application.product.dto.request.FindProductsQuery
import com.trading.tcg.application.product.dto.response.ProductDto
import com.trading.tcg.global.dto.Pageable

interface ProductPersistencePort {
    fun findProducts(query: FindProductsQuery): Pageable<List<ProductDto>>
}
