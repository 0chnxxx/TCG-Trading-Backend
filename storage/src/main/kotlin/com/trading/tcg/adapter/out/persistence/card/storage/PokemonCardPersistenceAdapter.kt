package com.trading.tcg.adapter.out.persistence.card.storage

import com.trading.tcg.card.dto.request.FindPokemonCardQuery
import com.trading.tcg.domain.card.Card
import com.trading.tcg.card.port.out.PokemonCardPersistencePort
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository

@Repository
class PokemonCardPersistenceAdapter(
    private val pokemonCardJpaRepository: PokemonCardJpaRepository
) : PokemonCardPersistencePort {
    override fun findPokemonCards(query: FindPokemonCardQuery): List<Card> {
        val pageable = Pageable.ofSize(query.size).withPage(query.page)
        val entities = pokemonCardJpaRepository.findAll(pageable)

        return entities.map { it.toDomain() }.toList()
    }

    override fun findPokemonCard(cardId: Long): Card {
        val entity = pokemonCardJpaRepository.findById(cardId)
        return entity.orElseThrow().toDomain()
    }
}
