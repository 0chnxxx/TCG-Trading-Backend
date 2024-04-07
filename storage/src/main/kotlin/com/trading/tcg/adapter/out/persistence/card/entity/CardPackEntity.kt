package com.trading.tcg.adapter.out.persistence.card.entity

import jakarta.persistence.*

@Entity
@Table(name = "card_pack")
data class CardPackEntity(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = false)
    val card: CardEntity,

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pack_id", nullable = false)
    val pack: CardPackCatalogEntity) {
}
