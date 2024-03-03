package com.trading.tcg.port.out

import com.trading.tcg.domain.PokemonCard

interface PokemonCardPersistencePort  {
    fun findPokemonCards(page: Int, size: Int): List<PokemonCard>
}
