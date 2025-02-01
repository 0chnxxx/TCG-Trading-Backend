package com.trading.tcg.application.pokemon.port.out

import com.trading.tcg.application.pokemon.domain.PokemonCard
import com.trading.tcg.application.pokemon.dto.request.FindPokemonCardsQuery
import java.util.*

interface PokemonCardPersistencePort {
    fun findPokemonCards(query: FindPokemonCardsQuery): List<PokemonCard>
    fun findPokemonCard(cardId: Long): Optional<PokemonCard>
    fun countPokemonCards(): Long
}
