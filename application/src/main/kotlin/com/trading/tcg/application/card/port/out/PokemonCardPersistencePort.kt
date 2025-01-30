package com.trading.tcg.application.card.port.out

import com.trading.tcg.application.card.domain.PokemonCard
import com.trading.tcg.application.card.dto.request.FindPokemonCardsQuery
import java.util.*

interface PokemonCardPersistencePort {
    fun findPokemonCards(query: FindPokemonCardsQuery): List<PokemonCard>
    fun findPokemonCard(cardId: Long): Optional<PokemonCard>
}
