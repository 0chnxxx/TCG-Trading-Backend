package com.trading.tcg.adapter.out.persistence.storage

import com.trading.tcg.domain.PokemonCard
import com.trading.tcg.port.out.PokemonCardPersistencePort
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class PokemonCardPersistenceAdapter(
    private val pokemonCardJpaRepository: PokemonCardJpaRepository
) : PokemonCardPersistencePort {
    override fun findPokemonCards(page: Int, size: Int): List<PokemonCard> {
        val pageable = Pageable.ofSize(size).withPage(page)
        val entities = pokemonCardJpaRepository.findAllWithSkills(pageable)

        return entities.map { it.toDomain() }.toList()
    }
}
