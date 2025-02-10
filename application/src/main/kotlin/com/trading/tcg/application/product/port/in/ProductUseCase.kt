package com.trading.tcg.application.product.port.`in`

import com.trading.tcg.application.product.dto.request.FindProductQuery
import com.trading.tcg.application.product.dto.request.FindProductsQuery
import com.trading.tcg.application.product.dto.response.ProductCatalogDto
import com.trading.tcg.application.product.dto.response.ProductCountDto
import com.trading.tcg.application.product.dto.response.ProductDetailDto
import com.trading.tcg.application.product.dto.response.ProductDto
import com.trading.tcg.global.dto.Response

interface ProductUseCase {
    fun findProductCatalog(): Response<ProductCatalogDto>
    fun findProducts(query: FindProductsQuery): Response<List<ProductDto>>
    fun findProductCount(query: FindProductsQuery): Response<ProductCountDto>
    fun findProduct(query: FindProductQuery): Response<ProductDetailDto>
}
