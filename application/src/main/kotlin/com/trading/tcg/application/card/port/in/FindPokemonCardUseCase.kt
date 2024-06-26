package com.trading.tcg.application.card.port.`in`

import com.trading.tcg.application.card.dto.request.FindPokemonCardQuery
import com.trading.tcg.application.card.dto.response.PokemonCardDetailDto
import com.trading.tcg.application.card.dto.response.PokemonCardDto
import com.trading.tcg.global.dto.Provider
import com.trading.tcg.global.dto.Response

interface FindPokemonCardUseCase {
    fun findPokemonCards(
        provider: Provider,
        query: FindPokemonCardQuery
    ): Response<List<PokemonCardDto>>

    fun findPokemonCard(
        provider: Provider,
        cardId: Long
    ): Response<PokemonCardDetailDto>
}
