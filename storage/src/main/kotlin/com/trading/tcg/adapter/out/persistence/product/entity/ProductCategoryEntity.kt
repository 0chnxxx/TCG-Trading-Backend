package com.trading.tcg.adapter.out.persistence.product.entity

import jakarta.persistence.*

@Entity
@Table(name = "product_category")
class ProductCategoryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @Column(name = "query_name", nullable = false)
    val queryName: String,

    @Column(name = "display_name", nullable = false)
    val displayName: String,

    @Column(name = "display_order", nullable = false)
    val displayOrder: Int,

    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    val filters: List<ProductCategoryFilterEntity>
)
