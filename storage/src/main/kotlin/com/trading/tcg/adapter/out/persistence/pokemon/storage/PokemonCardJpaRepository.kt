package com.trading.tcg.adapter.out.persistence.pokemon.storage

import com.trading.tcg.adapter.out.persistence.pokemon.entity.PokemonCardEntity
import org.springframework.data.jpa.repository.JpaRepository

interface PokemonCardJpaRepository : JpaRepository<PokemonCardEntity, Long>
