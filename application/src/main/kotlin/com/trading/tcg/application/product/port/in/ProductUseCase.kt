package com.trading.tcg.application.product.port.`in`

import com.trading.tcg.application.product.dto.request.FindProductBidsQuery
import com.trading.tcg.application.product.dto.request.FindProductQuery
import com.trading.tcg.application.product.dto.request.FindProductsQuery
import com.trading.tcg.application.product.dto.response.*
import com.trading.tcg.global.dto.Response

interface ProductUseCase {
    fun findProductCatalog(): Response<ProductCatalogDto>
    fun findProducts(query: FindProductsQuery): Response<List<ProductDto>>
    fun findProductCount(query: FindProductsQuery): Response<ProductCountDto>
    fun findProduct(query: FindProductQuery): Response<ProductDetailDto>
    fun findProductBids(query: FindProductBidsQuery): Response<List<ProductBidDto>>
}
