package com.trading.tcg.application.product.port.`in`

import com.trading.tcg.application.product.dto.request.*
import com.trading.tcg.application.product.dto.response.*
import com.trading.tcg.global.dto.Pageable
import com.trading.tcg.global.dto.Provider

interface ProductUseCase {
    fun findProductCatalog(): ProductCatalogDto
    fun findProductCount(query: FindProductsQuery): Long
    fun findProducts(query: FindProductsQuery): Pageable<List<ProductDto>>
    fun findProduct(query: FindProductQuery): ProductDetailDto
    fun findProductBidHistories(provider: Provider, query: FindProductBidHistoryQuery): Pageable<List<ProductBidHistoryDto>>
    fun findProductPriceTrend(query: FindProductBidTrendQuery): ProductPriceTrendDto
    fun updateProductBookmark(command: UpdateProductBookmarkCommand)
    fun placeProductBuyBid(command: PlaceProductBuyBidCommand)
    fun placeProductSellBid(command: PlaceProductSellBidCommand)
}
