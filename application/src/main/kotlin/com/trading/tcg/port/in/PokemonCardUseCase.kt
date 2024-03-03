package com.trading.tcg.port.`in`

import com.trading.tcg.domain.PokemonCard

interface PokemonCardUseCase {
    fun findPokemonCards(page: Int, size: Int): List<PokemonCard>
}
