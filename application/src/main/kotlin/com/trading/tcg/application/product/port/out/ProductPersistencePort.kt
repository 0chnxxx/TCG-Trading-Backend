package com.trading.tcg.application.product.port.out

import com.trading.tcg.application.product.dto.request.FindProductBidHistoryQuery
import com.trading.tcg.application.product.dto.request.FindProductQuery
import com.trading.tcg.application.product.dto.request.FindProductsQuery
import com.trading.tcg.application.product.dto.response.*
import com.trading.tcg.global.dto.Pageable
import com.trading.tcg.product.domain.ProductBuyBid
import com.trading.tcg.product.domain.ProductCategory
import com.trading.tcg.product.domain.ProductDealBid
import com.trading.tcg.product.domain.ProductSellBid
import java.time.LocalDateTime

interface ProductPersistencePort {
    fun findProductDtos(query: FindProductsQuery): Pageable<List<ProductDto>>
    fun findProductDto(query: FindProductQuery): ProductDetailDto?
    fun findProductCategoriesWithFilters(): List<ProductCategory>
    fun findProductBuyBids(query: FindProductBidHistoryQuery): Pageable<List<ProductBuyBid>>
    fun findProductSellBids(query: FindProductBidHistoryQuery): Pageable<List<ProductSellBid>>
    fun findProductDealBids(query: FindProductBidHistoryQuery): Pageable<List<ProductDealBid>>
    fun findProductDealsByProductIdAfterDateTime(productId: Long, dateTime: LocalDateTime): List<ProductDealBid>
}
