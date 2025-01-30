package com.trading.tcg.application.card.dto.response

import com.trading.tcg.application.card.domain.PokemonCard

data class PokemonCardDto(
    val id: Long? = null,
    val cardImage: String
) {
    companion object {
        @JvmStatic
        fun ofDomain(pokemonCard: PokemonCard): PokemonCardDto {
            return PokemonCardDto(
                id = pokemonCard.id,
                cardImage = pokemonCard.image
            )
        }
    }
}
