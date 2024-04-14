package com.trading.tcg.card.port.out

import com.trading.tcg.card.dto.request.FindPokemonCardQuery
import com.trading.tcg.card.domain.Card

interface PokemonCardPersistencePort  {
    fun findPokemonCards(query: FindPokemonCardQuery): List<Card>
    fun findPokemonCard(cardId: Long): Card
}
