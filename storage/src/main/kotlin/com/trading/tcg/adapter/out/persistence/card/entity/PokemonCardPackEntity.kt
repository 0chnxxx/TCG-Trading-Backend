package com.trading.tcg.adapter.out.persistence.card.entity

import com.trading.tcg.adapter.out.persistence.global.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "pokemon_card_pack")
class PokemonCardPackEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "image", nullable = false)
    val image: String
) : BaseEntity() {
}
