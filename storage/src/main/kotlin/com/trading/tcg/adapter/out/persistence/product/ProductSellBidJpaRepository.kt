package com.trading.tcg.adapter.out.persistence.product

import com.trading.tcg.product.domain.ProductSellBid
import org.springframework.data.jpa.repository.JpaRepository

interface ProductSellBidJpaRepository: JpaRepository<ProductSellBid, Long>
