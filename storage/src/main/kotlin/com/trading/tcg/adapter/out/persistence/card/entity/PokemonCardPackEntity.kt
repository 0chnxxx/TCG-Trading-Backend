package com.trading.tcg.adapter.out.persistence.card.entity

import com.trading.tcg.domain.card.PokemonCardPack
import jakarta.persistence.*

@Entity
@Table(name = "pokemon_card_pack")
class PokemonCardPackEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private val id: Long? = null,

    @Column(name = "name", nullable = false)
    private val name: String) {

    fun toDomain(): PokemonCardPack {
        return PokemonCardPack(
            id = id,
            name = name
        )
    }
}
