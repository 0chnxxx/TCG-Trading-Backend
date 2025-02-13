package com.trading.tcg.card.domain

import jakarta.persistence.*

@Entity
@Table(name = "pokemon_card_skill")
data class PokemonCardSkill(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = false)
    val card: PokemonCard,

    @Column(name = "name")
    val name: String?,

    @Column(name = "type")
    val type: String?,

    @Column(name = "damage")
    val damage: String?,

    @Column(name = "description", nullable = false)
    val description: String?
)
