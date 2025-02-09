package com.trading.tcg.adapter.out.persistence.pokemon.entity

import com.trading.tcg.application.card.domain.PokemonCardPack
import jakarta.persistence.*

@Entity
@Table(name = "pokemon_card_pack")
class PokemonCardPackEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long?,

    @Column(name = "name", nullable = false)
    val name: String
) {
    fun toDomain(): PokemonCardPack {
        return PokemonCardPack(
            id = id,
            name = name
        )
    }
}
