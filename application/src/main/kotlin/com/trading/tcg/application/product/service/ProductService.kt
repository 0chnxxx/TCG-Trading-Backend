package com.trading.tcg.application.product.service

import com.trading.tcg.application.product.dto.request.*
import com.trading.tcg.application.product.dto.response.*
import com.trading.tcg.application.product.port.`in`.ProductUseCase
import com.trading.tcg.application.product.port.out.ProductPersistencePort
import com.trading.tcg.application.user.port.out.UserPersistencePort
import com.trading.tcg.global.dto.Pageable
import com.trading.tcg.global.dto.Provider
import com.trading.tcg.global.exception.CustomException
import com.trading.tcg.product.domain.ProductBidType
import com.trading.tcg.product.domain.ProductBookmark
import com.trading.tcg.product.exception.ProductErrorCode
import com.trading.tcg.user.exception.UserErrorCode
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.LocalDateTime

@Service
@RequiredArgsConstructor
class ProductService(
    private val productPersistencePort: ProductPersistencePort,
    private val userPersistencePort: UserPersistencePort
): ProductUseCase {
    @Transactional(readOnly = true)
    override fun findProductCatalog(): ProductCatalogDto {
        return productPersistencePort.findProductCatalog()
    }

    @Transactional(readOnly = true)
    override fun findProducts(query: FindProductsQuery): Pageable<List<ProductDto>> {
        return productPersistencePort.findProducts(query)
    }

    @Transactional(readOnly = true)
    override fun findProduct(query: FindProductQuery): ProductDetailDto {
        return productPersistencePort.findProduct(query)
            ?: throw CustomException(ProductErrorCode.NOT_FOUND_PRODUCT)
    }

    @Transactional(readOnly = true)
    override fun findProductBidHistories(provider: Provider, query: FindProductBidHistoryQuery): Pageable<List<ProductBidHistoryDto>> {
        return when (query.type) {
            ProductBidType.BUY -> {
                val productBuyBids = productPersistencePort.findProductBuyBids(query)

                Pageable(
                    pageResult = productBuyBids.pageResult,
                    data = productBuyBids.data.map { ProductBidHistoryDto.ofBuy(provider, it) }
                )
            }
            ProductBidType.SELL -> {
                val productSellBids = productPersistencePort.findProductSellBids(query)

                Pageable(
                    pageResult = productSellBids.pageResult,
                    data = productSellBids.data.map { ProductBidHistoryDto.ofSell(provider, it) }
                )
            }
            ProductBidType.DEAL -> {
                val productDealBids = productPersistencePort.findProductDealBids(query)

                Pageable(
                    pageResult = productDealBids.pageResult,
                    data = productDealBids.data.map { ProductBidHistoryDto.ofDeal(provider, it) }
                )
            }
            else -> throw CustomException(ProductErrorCode.INVALID_PRODUCT_BID_TYPE)
        }
    }

    @Transactional(readOnly = true)
    override fun findProductPriceTrend(query: FindProductBidTrendQuery): ProductPriceTrendDto {
        val sixMonthsAgo = LocalDateTime.now().minusMonths(6)
        val productDeals = productPersistencePort.findProductDealsByProductIdAfterDateTime(query.productId, sixMonthsAgo)

        val totalCount = productDeals.size
        val maxBucketCount = 12
        val minBucketCount = Math.min(totalCount, maxBucketCount)
        val countPerBucket = (totalCount / minBucketCount).let { if (it == 0) 1 else it }

        val averagePrices = productDeals
            .chunked(countPerBucket)
            .map { chunk -> chunk.map { it.price.toDouble() }.average() }
            .map { averagePrice -> averagePrice.toLong() }

        val productPriceTrendDto = ProductPriceTrendDto(
            prices = averagePrices,
            minPrice = averagePrices.minOrNull(),
            maxPrice = averagePrices.maxOrNull()
        )

        return productPriceTrendDto
    }

    @Transactional
    override fun updateProductBookmark(command: UpdateProductBookmarkCommand) {
        val productBookmark = productPersistencePort.findProductBookmark(ProductBookmark.ProductBookmarkId(command.userId, command.productId))

        if (productBookmark.isPresent) {
            productPersistencePort.deleteProductBookmark(productBookmark.get())
        } else {
            val user = userPersistencePort.findById(command.userId)
                .orElseThrow { CustomException(UserErrorCode.NOT_FOUND_USER) }
            val product = productPersistencePort.findById(command.productId)
                .orElseThrow { CustomException(ProductErrorCode.NOT_FOUND_PRODUCT) }

            val newProductBookmark = ProductBookmark(
                user = user,
                product = product
            )

            productPersistencePort.saveProductBookmark(newProductBookmark)
        }
    }
}
