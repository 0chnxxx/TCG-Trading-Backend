package com.trading.tcg.adapter.out.persistence.card.entity

import jakarta.persistence.*

@Entity
@Table(name = "card_category")
data class CardCategoryEntity(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = false)
    val card: CardEntity,

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    val category: CardCategoryCatalogEntity) {
}
