package com.trading.tcg.application.product.port.`in`

import com.trading.tcg.application.product.dto.request.*
import com.trading.tcg.application.product.dto.response.*
import com.trading.tcg.global.dto.Provider
import com.trading.tcg.global.dto.Response

interface ProductUseCase {
    fun findProductCatalog(): Response<ProductCatalogDto>
    fun findProducts(query: FindProductsQuery): Response<List<ProductDto>>
    fun findProduct(query: FindProductQuery): Response<ProductDetailDto>
    fun findProductBidHistories(provider: Provider, query: FindProductBidHistoryQuery): Response<List<ProductBidHistoryDto>>
    fun findProductPriceTrend(query: FindProductBidTrendQuery): Response<ProductPriceTrendDto>
    fun updateProductBookmark(command: UpdateProductBookmarkCommand)
}
