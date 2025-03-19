package com.trading.tcg.adapter.`in`.api.product.controller

import com.trading.tcg.adapter.`in`.api.product.dto.*
import com.trading.tcg.adapter.`in`.swagger.product.ProductSwagger
import com.trading.tcg.application.product.dto.response.*
import com.trading.tcg.application.product.port.`in`.ProductUseCase
import com.trading.tcg.global.dto.Provider
import com.trading.tcg.global.dto.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ProductController(
    private val productUseCase: ProductUseCase
): ProductSwagger {
    @GetMapping("/products/catalog")
    override fun findProductCatalog(
        @AuthenticationPrincipal
        provider: Provider
    ): ResponseEntity<Response<ProductCatalogDto>> {
        val productCatalog = productUseCase.findProductCatalog()

        val response = Response.of(
            data = productCatalog
        )

        return ResponseEntity(response, HttpStatus.OK)
    }

    @GetMapping("/products/count")
    override fun findProductCount(
        @AuthenticationPrincipal
        provider: Provider,
        request: FindProductsRequest
    ): ResponseEntity<Response<Long>> {
        val count = productUseCase.findProductCount(request.toQuery(provider))

        val response = Response.of(
            data = count
        )

        return ResponseEntity(response, HttpStatus.OK)
    }

    @GetMapping("/products")
    override fun findProducts(
        @AuthenticationPrincipal
        provider: Provider,
        request: FindProductsRequest
    ): ResponseEntity<Response<List<ProductDto>>> {
        val products = productUseCase.findProducts(request.toQuery(provider))

        val response = Response.of(
            pageResult = products.pageResult,
            data = products.data
        )

        return ResponseEntity(response, HttpStatus.OK)
    }

    @GetMapping("/products/{productId}")
    override fun findProduct(
        @AuthenticationPrincipal
        provider: Provider,
        request: FindProductRequest
    ): ResponseEntity<Response<ProductDetailDto>> {
        val product = productUseCase.findProduct(request.toQuery(provider))

        val response = Response.of(
            data = product
        )

        return ResponseEntity(response, HttpStatus.OK)
    }

    @GetMapping("/products/{productId}/bids/histories")
    override fun findProductBidHistories(
        @AuthenticationPrincipal
        provider: Provider,
        request: FindProductBidHistoryRequest
    ): ResponseEntity<Response<List<ProductBidHistoryDto>>> {
        val productBidHistories = productUseCase.findProductBidHistories(provider, request.toQuery(provider))

        val response = Response.of(
            pageResult = productBidHistories.pageResult,
            data = productBidHistories.data
        )

        return ResponseEntity(response, HttpStatus.OK)
    }

    @GetMapping("/products/{productId}/bids/trend")
    override fun findProductPriceTrend(
        @AuthenticationPrincipal
        provider: Provider,
        request: FindProductPriceTrendRequest
    ): ResponseEntity<Response<ProductPriceTrendDto>> {
        val productPriceTrend = productUseCase.findProductPriceTrend(request.toQuery(provider))

        val response = Response.of(
            data = productPriceTrend
        )

        return ResponseEntity(response, HttpStatus.OK)
    }

    @PostMapping("/products/{productId}/bookmark")
    override fun updateProductBookmark(
        @AuthenticationPrincipal
        provider: Provider,
        request: UpdateProductBookmarkRequest
    ): ResponseEntity<Unit> {
        productUseCase.updateProductBookmark(request.toCommand(provider))
        return ResponseEntity(HttpStatus.OK)
    }
}
