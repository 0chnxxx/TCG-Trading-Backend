package com.trading.tcg.application.pokemon.port.`in`

import com.trading.tcg.application.pokemon.dto.request.FindPokemonCardsQuery
import com.trading.tcg.application.pokemon.dto.response.PokemonCardDetailDto
import com.trading.tcg.application.pokemon.dto.response.PokemonCardDto
import com.trading.tcg.global.dto.Provider
import com.trading.tcg.global.dto.Response

interface FindPokemonCardUseCase {
    fun findPokemonCards(
        provider: Provider,
        query: FindPokemonCardsQuery
    ): Response<List<PokemonCardDto>>

    fun findPokemonCard(
        provider: Provider,
        cardId: Long
    ): Response<PokemonCardDetailDto>
}
