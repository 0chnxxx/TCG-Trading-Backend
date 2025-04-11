package com.trading.tcg.adapter.out.persistence.product

import com.trading.tcg.product.domain.ProductBuyBid
import org.springframework.data.jpa.repository.JpaRepository

interface ProductBuyBidJpaRepository: JpaRepository<ProductBuyBid, Long>
