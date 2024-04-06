package com.trading.tcg.card.port.out

import com.trading.tcg.card.dto.request.FindPokemonCardQuery
import com.trading.tcg.domain.card.PokemonCard

interface PokemonCardPersistencePort  {
    fun findPokemonCards(query: FindPokemonCardQuery): List<PokemonCard>
    fun findPokemonCard(cardId: Long): PokemonCard
}
