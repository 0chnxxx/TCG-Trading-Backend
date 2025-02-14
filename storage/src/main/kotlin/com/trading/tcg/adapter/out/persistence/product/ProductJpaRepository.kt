package com.trading.tcg.adapter.out.persistence.product

import com.trading.tcg.product.domain.Product
import org.springframework.data.jpa.repository.JpaRepository

interface ProductJpaRepository: JpaRepository<Product, Long>
