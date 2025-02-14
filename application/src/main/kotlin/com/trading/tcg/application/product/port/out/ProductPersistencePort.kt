package com.trading.tcg.application.product.port.out

import com.trading.tcg.application.product.dto.request.FindProductBidTrendQuery
import com.trading.tcg.application.product.dto.request.FindProductBidsQuery
import com.trading.tcg.application.product.dto.request.FindProductQuery
import com.trading.tcg.application.product.dto.request.FindProductsQuery
import com.trading.tcg.application.product.dto.response.*
import com.trading.tcg.global.dto.Pageable
import com.trading.tcg.product.domain.ProductCategory

interface ProductPersistencePort {
    fun findProductDtos(query: FindProductsQuery): Pageable<List<ProductDto>>
    fun findProductDto(query: FindProductQuery): ProductDetailDto?
    fun findProductCategoriesWithFilters(): List<ProductCategory>
    fun findProductBuyBids(query: FindProductBidsQuery): Pageable<List<ProductBidDto>>
    fun findProductSellBids(query: FindProductBidsQuery): Pageable<List<ProductBidDto>>
    fun findProductDealBids(query: FindProductBidsQuery): Pageable<List<ProductBidDto>>
    fun findProductBidTrend(query: FindProductBidTrendQuery): ProductBidTrendDto
}
