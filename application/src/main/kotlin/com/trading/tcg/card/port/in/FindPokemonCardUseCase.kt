package com.trading.tcg.card.port.`in`

import com.trading.tcg.card.dto.request.FindPokemonCardQuery
import com.trading.tcg.card.dto.response.PokemonCardDetailDto
import com.trading.tcg.card.dto.response.PokemonCardDto

interface FindPokemonCardUseCase {
    fun findPokemonCards(query: FindPokemonCardQuery): List<PokemonCardDto>
    fun findPokemonCard(cardId: Long): PokemonCardDetailDto
}
