package com.trading.tcg.adapter.out.persistence.card.entity

import jakarta.persistence.*

@Entity
@Table(name = "card_category_catalog")
class CardCategoryCatalogEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @Column(name = "name", nullable = false)
    val name: String) {
}
