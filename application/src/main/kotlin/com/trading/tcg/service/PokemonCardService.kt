package com.trading.tcg.service

import com.trading.tcg.domain.PokemonCard
import com.trading.tcg.port.`in`.PokemonCardUseCase
import com.trading.tcg.port.out.PokemonCardPersistencePort
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class PokemonCardService(
    private val pokemonCardPersistencePort : PokemonCardPersistencePort
) : PokemonCardUseCase {
    override fun findPokemonCards(page: Int, size: Int): List<PokemonCard> {
        return pokemonCardPersistencePort.findPokemonCards(page, size)
    }
}
