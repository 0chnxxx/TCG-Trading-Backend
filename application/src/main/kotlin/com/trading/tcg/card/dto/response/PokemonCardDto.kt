package com.trading.tcg.card.dto.response

import com.trading.tcg.domain.card.Card

data class PokemonCardDto(
    val id: Long? = null,
    val cardImage: String
) {
    companion object {
        fun ofDomain(card: Card): PokemonCardDto {
            return PokemonCardDto(
                id = card.id,
                cardImage = card.image
            )
        }
    }
}
