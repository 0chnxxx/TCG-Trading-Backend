package com.trading.tcg.application.product.port.out

import com.trading.tcg.application.product.dto.request.FindProductBidsQuery
import com.trading.tcg.application.product.dto.request.FindProductQuery
import com.trading.tcg.application.product.dto.request.FindProductsQuery
import com.trading.tcg.application.product.dto.response.ProductBidDto
import com.trading.tcg.application.product.dto.response.ProductCatalogDto
import com.trading.tcg.application.product.dto.response.ProductDetailDto
import com.trading.tcg.application.product.dto.response.ProductDto
import com.trading.tcg.global.dto.Pageable

interface ProductPersistencePort {
    fun findProductCatalog(): ProductCatalogDto
    fun findProducts(query: FindProductsQuery): Pageable<List<ProductDto>>
    fun countProducts(query: FindProductsQuery): Long
    fun findProduct(query: FindProductQuery): ProductDetailDto?
    fun findProductBids(query: FindProductBidsQuery): Pageable<List<ProductBidDto>>
    fun countProductBids(query: FindProductBidsQuery): Long
}
