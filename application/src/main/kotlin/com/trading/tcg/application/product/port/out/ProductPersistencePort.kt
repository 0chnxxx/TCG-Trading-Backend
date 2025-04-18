package com.trading.tcg.application.product.port.out

import com.trading.tcg.application.product.dto.request.FindProductBidHistoryQuery
import com.trading.tcg.application.product.dto.request.FindProductQuery
import com.trading.tcg.application.product.dto.request.FindProductsQuery
import com.trading.tcg.application.product.dto.response.*
import com.trading.tcg.global.dto.Pageable
import com.trading.tcg.product.domain.*
import java.time.LocalDateTime
import java.util.*

interface ProductPersistencePort {
    fun findById(id: Long): Optional<Product>
    fun findProductCatalog(): ProductCatalogDto
    fun findProductTotalCount(): Long
    fun findProductCount(query: FindProductsQuery): Long
    fun findProducts(query: FindProductsQuery): Pageable<List<ProductDto>>
    fun findProduct(query: FindProductQuery): ProductDetailDto?
    fun findProductDealBids(query: FindProductBidHistoryQuery): Pageable<List<ProductDealBid>>
    fun findProductDealsByProductIdAfterDateTime(productId: Long, dateTime: LocalDateTime): List<ProductDealBid>
    fun findProductBuyBids(query: FindProductBidHistoryQuery): Pageable<List<ProductBuyBid>>
    fun saveProductBuyBid(productBuyBid: ProductBuyBid)
    fun findProductSellBids(query: FindProductBidHistoryQuery): Pageable<List<ProductSellBid>>
    fun saveProductSellBid(productSellBid: ProductSellBid)
    fun findProductBookmark(id: ProductBookmark.ProductBookmarkId): Optional<ProductBookmark>
    fun saveProductBookmark(productBookmark: ProductBookmark)
    fun deleteProductBookmark(productBookmark: ProductBookmark)
}
