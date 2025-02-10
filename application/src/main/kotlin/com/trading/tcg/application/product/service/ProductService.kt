package com.trading.tcg.application.product.service

import com.trading.tcg.application.product.dto.request.FindProductQuery
import com.trading.tcg.application.product.dto.request.FindProductsQuery
import com.trading.tcg.application.product.dto.response.ProductCatalogDto
import com.trading.tcg.application.product.dto.response.ProductCountDto
import com.trading.tcg.application.product.dto.response.ProductDetailDto
import com.trading.tcg.application.product.dto.response.ProductDto
import com.trading.tcg.application.product.port.`in`.ProductUseCase
import com.trading.tcg.application.product.port.out.ProductPersistencePort
import com.trading.tcg.global.dto.Response
import com.trading.tcg.global.exception.CustomException
import com.trading.tcg.global.exception.ServiceErrorCode
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@RequiredArgsConstructor
class ProductService(
    private val productPersistencePort: ProductPersistencePort
): ProductUseCase {
    @Transactional(readOnly = true)
    override fun findProductCatalog(): Response<ProductCatalogDto> {
        val productCatalog = productPersistencePort.findProductCatalog()

        return Response.of(
            data = productCatalog
        )
    }

    @Transactional(readOnly = true)
    override fun findProducts(query: FindProductsQuery): Response<List<ProductDto>> {
        val products = productPersistencePort.findProducts(query)

        return Response.of(
            pageResult = products.pageResult,
            data = products.data
        )
    }

    @Transactional(readOnly = true)
    override fun findProductCount(query: FindProductsQuery): Response<ProductCountDto> {
        val count = productPersistencePort.countProducts(query)

        return Response.of(
            data = ProductCountDto(
                count = count
            )
        )
    }

    @Transactional(readOnly = true)
    override fun findProduct(query: FindProductQuery): Response<ProductDetailDto> {
        val product = productPersistencePort.findProduct(query)
            ?: throw CustomException(ServiceErrorCode.NOT_FOUND_PRODUCT)

        return Response.of(
            data = product
        )
    }
}
