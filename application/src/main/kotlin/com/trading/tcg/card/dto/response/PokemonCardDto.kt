package com.trading.tcg.card.dto.response

import com.trading.tcg.card.domain.Card

data class PokemonCardDto(
    val id: Long? = null,
    val cardImage: String
) {
    companion object {
        @JvmStatic
        fun ofDomain(card: Card): PokemonCardDto {
            return PokemonCardDto(
                id = card.id,
                cardImage = card.image
            )
        }
    }
}
