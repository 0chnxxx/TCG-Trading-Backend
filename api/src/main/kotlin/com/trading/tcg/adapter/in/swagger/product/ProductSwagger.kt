package com.trading.tcg.adapter.`in`.swagger.product

import com.trading.tcg.adapter.`in`.api.product.dto.FindProductRequest
import com.trading.tcg.adapter.`in`.api.product.dto.FindProductsRequest
import com.trading.tcg.application.product.dto.response.ProductCatalogDto
import com.trading.tcg.application.product.dto.response.ProductCountDto
import com.trading.tcg.application.product.dto.response.ProductDetailDto
import com.trading.tcg.application.product.dto.response.ProductDto
import com.trading.tcg.global.dto.Provider
import com.trading.tcg.global.dto.Response
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity

@Tag(name = "상품")
interface ProductSwagger {
    @Operation(summary = "상품 카탈로그 조회", description = "상품 카탈로그를 조회한다.")
    fun findProductCatalog(
        provider: Provider
    ): ResponseEntity<Response<ProductCatalogDto>>

    @Operation(summary = "상품 리스트 조회", description = "상품 리스트를 조회한다.")
    fun findProducts(
        provider: Provider,
        request: FindProductsRequest
    ): ResponseEntity<Response<List<ProductDto>>>

    @Operation(summary = "상품 갯수 조회", description = "상품 갯수를 조회한다.")
    fun findProductCount(
        provider: Provider,
        request: FindProductsRequest
    ): ResponseEntity<Response<ProductCountDto>>

    @Operation(summary = "상품 디테일 조회", description = "상품 디테일을 조회한다.")
    fun findProduct(
        provider: Provider,
        request: FindProductRequest
    ): ResponseEntity<Response<ProductDetailDto>>
}
