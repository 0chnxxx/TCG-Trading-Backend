package com.trading.tcg.application.pokemon.dto.response

import com.trading.tcg.application.pokemon.domain.PokemonCard

data class PokemonCardDto(
    val id: Long? = null,
    val name: String,
    val image: String
) {
    companion object {
        @JvmStatic
        fun ofDomain(pokemonCard: PokemonCard): PokemonCardDto {
            return PokemonCardDto(
                id = pokemonCard.id,
                name = pokemonCard.name,
                image = pokemonCard.image
            )
        }
    }
}
