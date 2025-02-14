package com.trading.tcg.adapter.out.persistence.product

import com.trading.tcg.product.domain.ProductCategory
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ProductCategoryJpaRepository: JpaRepository<ProductCategory, Long> {
    @Query("""
        SELECT pc
        FROM ProductCategory pc
        LEFT JOIN FETCH pc.filters
    """)
    fun findAllWithFilters(): List<ProductCategory>
}
