package com.trading.tcg.application.card.port.out

import com.trading.tcg.application.card.domain.Card
import com.trading.tcg.application.card.dto.request.FindPokemonCardQuery
import java.util.*

interface PokemonCardPersistencePort {
    fun findPokemonCards(query: FindPokemonCardQuery): List<Card>
    fun findPokemonCard(cardId: Long): Optional<Card>
}
