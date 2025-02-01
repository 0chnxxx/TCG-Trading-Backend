package com.trading.tcg.adapter.out.persistence.pokemon.storage

import com.trading.tcg.adapter.out.persistence.pokemon.entity.PokemonCardEntity
import com.trading.tcg.application.pokemon.domain.PokemonCard
import com.trading.tcg.application.pokemon.dto.request.FindPokemonCardsQuery
import com.trading.tcg.application.pokemon.port.out.PokemonCardPersistencePort
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class PokemonCardPersistenceAdapter(
    private val pokemonCardJpaRepository: PokemonCardJpaRepository
) : PokemonCardPersistencePort {
    override fun findPokemonCards(query: FindPokemonCardsQuery): List<PokemonCard> {
        val pageable: Pageable = Pageable.ofSize(query.size).withPage(query.page - 1)
        val entities: Page<PokemonCardEntity> = pokemonCardJpaRepository.findAll(pageable)

        return entities.map { it.toDomain() }.toList()
    }

    override fun findPokemonCard(cardId: Long): Optional<PokemonCard> {
        val entity: Optional<PokemonCardEntity> = pokemonCardJpaRepository.findById(cardId)

        return entity.map { it.toDomain() }
    }
}
