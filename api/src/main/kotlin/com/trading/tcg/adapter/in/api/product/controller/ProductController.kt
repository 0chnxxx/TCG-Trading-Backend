package com.trading.tcg.adapter.`in`.api.product.controller

import com.trading.tcg.adapter.`in`.api.product.dto.FindProductBidsRequest
import com.trading.tcg.adapter.`in`.api.product.dto.FindProductRequest
import com.trading.tcg.adapter.`in`.api.product.dto.FindProductsRequest
import com.trading.tcg.adapter.`in`.swagger.product.ProductSwagger
import com.trading.tcg.application.product.dto.response.*
import com.trading.tcg.application.product.port.`in`.ProductUseCase
import com.trading.tcg.global.dto.Provider
import com.trading.tcg.global.dto.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
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
        return ResponseEntity(productUseCase.findProductCatalog(), HttpStatus.OK)
    }

    @GetMapping("/products")
    override fun findProducts(
        @AuthenticationPrincipal
        provider: Provider,
        request: FindProductsRequest
    ): ResponseEntity<Response<List<ProductDto>>> {
        return ResponseEntity(productUseCase.findProducts(request.toQuery(provider)), HttpStatus.OK)
    }

    @GetMapping("/products/count")
    override fun findProductCount(
        provider: Provider,
        request: FindProductsRequest
    ): ResponseEntity<Response<ProductCountDto>> {
        return ResponseEntity(productUseCase.findProductCount(request.toQuery(provider)), HttpStatus.OK)
    }

    @GetMapping("/products/{productId}")
    override fun findProduct(
        provider: Provider,
        request: FindProductRequest
    ): ResponseEntity<Response<ProductDetailDto>> {
        return ResponseEntity(productUseCase.findProduct(request.toQuery(provider)), HttpStatus.OK)
    }

    @GetMapping("/products/{productId}/bids")
    override fun findProductBids(
        provider: Provider,
        request: FindProductBidsRequest
    ): ResponseEntity<Response<List<ProductBidDto>>> {
        return ResponseEntity(productUseCase.findProductBids(request.toQuery(provider)), HttpStatus.OK)
    }
}
