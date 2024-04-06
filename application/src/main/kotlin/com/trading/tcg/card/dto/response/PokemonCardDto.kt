package com.trading.tcg.card.dto.response

import com.trading.tcg.domain.card.PokemonCard

data class PokemonCardDto(
    val id: Long? = null,
    val cardImage: String
) {
    companion object {
        fun ofDomain(pokemonCard: PokemonCard): PokemonCardDto {
            return PokemonCardDto(
                id = pokemonCard.id,
                cardImage = pokemonCard.cardImage
            )
        }
    }
}
