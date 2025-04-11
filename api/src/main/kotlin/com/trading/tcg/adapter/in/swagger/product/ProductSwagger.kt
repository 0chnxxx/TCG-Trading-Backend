package com.trading.tcg.adapter.`in`.swagger.product

import com.trading.tcg.adapter.`in`.api.product.dto.*
import com.trading.tcg.application.product.dto.response.*
import com.trading.tcg.global.dto.Provider
import com.trading.tcg.global.dto.Response
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity

@Tag(name = "상품")
interface ProductSwagger {
    @Operation(summary = "상품 카탈로그 조회", description = "상품 카탈로그를 조회한다.")
    fun findProductCatalog(
        provider: Provider
    ): ResponseEntity<Response<ProductCatalogDto>>

    @Operation(summary = "상품 갯수 조회", description = "상품 갯수를 조회한다.")
    fun findProductCount(
        provider: Provider,
        request: FindProductsRequest
    ): ResponseEntity<Response<Long>>

    @Operation(summary = "상품 목록 조회", description = "상품 목록을 조회한다.")
    fun findProducts(
        provider: Provider,
        request: FindProductsRequest
    ): ResponseEntity<Response<List<ProductDto>>>

    @Operation(summary = "상품 세부사항 조회", description = "상품 세부사항을 조회한다.")
    fun findProduct(
        provider: Provider,
        request: FindProductRequest
    ): ResponseEntity<Response<ProductDetailDto>>

    @Operation(summary = "상품 입찰 내역 조회", description = "상품 입찰 내역을 조회한다.")
    fun findProductBidHistories(
        provider: Provider,
        request: FindProductBidHistoryRequest
    ): ResponseEntity<Response<List<ProductBidHistoryDto>>>

    @Operation(summary = "상품 입찰 시세 조회", description = "상품 입찰 시세를 조회한다.")
    fun findProductPriceTrend(
        provider: Provider,
        request: FindProductPriceTrendRequest
    ): ResponseEntity<Response<ProductPriceTrendDto>>

    @Operation(summary = "상품 북마크 상태 변경", description = "상품 북마크 상태를 토글로 변경한다.")
    fun updateProductBookmark(
        provider: Provider,
        request: UpdateProductBookmarkRequest
    ): ResponseEntity<Unit>

    @Operation(summary = "상품 구매 입찰", description = "상품에 대한 구매 입찰을 등록한다.")
    fun placeProductBuyBid(
        provider: Provider,
        productId: Long,
        request: PlaceProductBuyBidRequest
    ): ResponseEntity<Unit>

    @Operation(summary = "상품 판매 입찰", description = "상품에 대한 판매 입찰을 등록한다.")
    fun placeProductSellBid(
        provider: Provider,
        productId: Long,
        request: PlaceProductSellBidRequest
    ): ResponseEntity<Unit>
}
