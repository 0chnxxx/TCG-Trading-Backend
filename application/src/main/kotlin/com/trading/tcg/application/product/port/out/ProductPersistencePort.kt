package com.trading.tcg.application.product.port.out

import com.trading.tcg.application.product.dto.request.FindProductBidHistoryQuery
import com.trading.tcg.application.product.dto.request.FindProductQuery
import com.trading.tcg.application.product.dto.request.FindProductsQuery
import com.trading.tcg.application.product.dto.response.*
import com.trading.tcg.global.dto.Pageable
import com.trading.tcg.product.domain.*
import com.trading.tcg.user.domain.User
import java.time.LocalDateTime
import java.util.*

interface ProductPersistencePort {
    fun findById(id: Long): Optional<Product>
    fun findProductDtos(query: FindProductsQuery): Pageable<List<ProductDto>>
    fun findProductDto(query: FindProductQuery): ProductDetailDto?
    fun findProductCategoriesWithFilters(): List<ProductCategory>
    fun findProductBuyBids(query: FindProductBidHistoryQuery): Pageable<List<ProductBuyBid>>
    fun findProductSellBids(query: FindProductBidHistoryQuery): Pageable<List<ProductSellBid>>
    fun findProductDealBids(query: FindProductBidHistoryQuery): Pageable<List<ProductDealBid>>
    fun findProductDealsByProductIdAfterDateTime(productId: Long, dateTime: LocalDateTime): List<ProductDealBid>
    fun findProductBookmark(id: ProductBookmark.ProductBookmarkId): Optional<ProductBookmark>
    fun saveProductBookmark(productBookmark: ProductBookmark)
    fun deleteProductBookmark(productBookmark: ProductBookmark)
}
