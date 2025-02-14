package com.trading.tcg.adapter.out.persistence.product

import com.trading.tcg.product.domain.ProductBookmark
import org.springframework.data.jpa.repository.JpaRepository

interface ProductBookmarkJpaRepository: JpaRepository<ProductBookmark, ProductBookmark.ProductBookmarkId> {
}
