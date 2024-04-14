package com.trading.tcg.adapter.out.persistence.card.storage

import com.trading.tcg.adapter.out.persistence.card.entity.CardEntity
import com.trading.tcg.card.dto.request.FindPokemonCardQuery
import com.trading.tcg.card.domain.Card
import com.trading.tcg.card.port.out.PokemonCardPersistencePort
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Repository
import java.util.Optional

@Repository
class PokemonCardPersistenceAdapter(
    private val pokemonCardJpaRepository: PokemonCardJpaRepository
) : PokemonCardPersistencePort {
    override fun findPokemonCards(query: FindPokemonCardQuery): List<Card> {
        val pageable: Pageable = Pageable.ofSize(query.size).withPage(query.page)
        val entities: Page<CardEntity> = pokemonCardJpaRepository.findAll(pageable)

        return entities.map { it.toDomain() }.toList()
    }

    override fun findPokemonCard(cardId: Long): Optional<Card> {
        val entity: Optional<CardEntity> = pokemonCardJpaRepository.findById(cardId)

        return entity.map { it.toDomain() }
    }
}
