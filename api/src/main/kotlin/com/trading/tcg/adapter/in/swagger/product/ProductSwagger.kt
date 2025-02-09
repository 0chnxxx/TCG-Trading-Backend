package com.trading.tcg.adapter.`in`.swagger.product

import com.trading.tcg.adapter.`in`.api.product.dto.FindProductsRequest
import com.trading.tcg.application.product.dto.response.ProductDto
import com.trading.tcg.global.dto.Provider
import com.trading.tcg.global.dto.Response
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity

@Tag(name = "상품")
interface ProductSwagger {
    @Operation(summary = "상품 리스트 조회", description = "상품 리스트를 조회한다.")
    fun findProducts(
        provider: Provider,
        request: FindProductsRequest
    ): ResponseEntity<Response<List<ProductDto>>>

//    @Operation(summary = "상품 디테일 조회", description = "상품 디테일을 조회한다.")
//    fun findProductDetail(
//        provider: Provider,
//        @Parameter(name = "productId", description = "상품 번호", example = "1", required = true)
//        productId: Long
//    ): ResponseEntity<Response<ProductDetailDto>>
}
