package com.trading.tcg.adapter.out.persistence.card.entity

import jakarta.persistence.*

@Entity
@Table(name = "pokemon_card_category")
data class PokemonCardCategoryEntity(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = false)
    val card: PokemonCardEntity,

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    val category: PokemonCategoryEntity
) {
}
