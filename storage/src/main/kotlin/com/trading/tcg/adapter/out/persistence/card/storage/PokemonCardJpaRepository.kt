package com.trading.tcg.adapter.out.persistence.card.storage

import com.trading.tcg.adapter.out.persistence.card.entity.CardEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PokemonCardJpaRepository: JpaRepository<CardEntity, Long> {
}
