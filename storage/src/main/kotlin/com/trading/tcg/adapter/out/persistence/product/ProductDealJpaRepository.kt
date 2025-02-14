package com.trading.tcg.adapter.out.persistence.product

import com.trading.tcg.product.domain.ProductDealBid
import org.springframework.data.jpa.repository.JpaRepository
import java.time.LocalDateTime

interface ProductDealJpaRepository: JpaRepository<ProductDealBid, Long> {
    fun findAllByProductIdAndCreatedTimeAfter(productId: Long, dateTime: LocalDateTime): List<ProductDealBid>
}
