package com.trading.tcg.card.port.`in`

import com.trading.tcg.card.dto.request.FindPokemonCardQuery
import com.trading.tcg.card.dto.response.PokemonCardDetailDto
import com.trading.tcg.card.dto.response.PokemonCardDto
import com.trading.tcg.global.dto.Response

interface FindPokemonCardUseCase {
    fun findPokemonCards(query: FindPokemonCardQuery): Response<List<PokemonCardDto>>
    fun findPokemonCard(cardId: Long): Response<PokemonCardDetailDto>
}
