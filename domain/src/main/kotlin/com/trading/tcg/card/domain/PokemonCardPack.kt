package com.trading.tcg.card.domain

import jakarta.persistence.*

@Entity
@Table(name = "pokemon_card_pack")
class PokemonCardPack(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long?,

    @Column(name = "name", nullable = false)
    val name: String
)
