package com.trading.tcg.application.product.port.`in`

import com.trading.tcg.application.product.dto.request.FindProductsQuery
import com.trading.tcg.application.product.dto.response.ProductCatalogDto
import com.trading.tcg.application.product.dto.response.ProductCountDto
import com.trading.tcg.application.product.dto.response.ProductDto
import com.trading.tcg.global.dto.Response

interface ProductUseCase {
    fun findProducts(query: FindProductsQuery): Response<List<ProductDto>>
    fun findProductCatalog(): Response<ProductCatalogDto>
    fun findProductCount(query: FindProductsQuery): Response<ProductCountDto>
}
