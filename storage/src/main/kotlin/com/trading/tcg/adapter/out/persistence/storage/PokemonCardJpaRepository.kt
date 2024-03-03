package com.trading.tcg.adapter.out.persistence.storage

import com.trading.tcg.adapter.out.persistence.entity.PokemonCardEntity
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PokemonCardJpaRepository: JpaRepository<PokemonCardEntity, Long> {
    @Query("SELECT pc FROM PokemonCardEntity pc LEFT OUTER JOIN PokemonCardSkillEntity pcs ON pcs.card.id = pc.id")
    fun findAllWithSkills(pageable: Pageable): List<PokemonCardEntity>
}
