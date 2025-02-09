package com.trading.tcg.adapter.out.persistence.pokemon.entity

import jakarta.persistence.*

@Entity
@Table(name = "pokemon_card_category_catalog")
class PokemonCardCategoryEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long?,

    @Column(name = "name", nullable = false)
    val name: String
)
