package com.trading.tcg.adapter.out.persistence.product.entity

import jakarta.persistence.*

@Entity
@Table(name = "product_category_filter")
class ProductCategoryFilterEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    val category: ProductCategoryEntity,

    @Column(name = "query_name", nullable = false)
    val queryName: String,

    @Column(name = "display_name", nullable = false)
    val displayName: String,

    @Column(name = "display_order", nullable = false)
    val displayOrder: Int,

    @Column(name = "option", nullable = false)
    val option: String
)
