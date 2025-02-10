package com.trading.tcg.adapter.out.persistence.card.entity

import com.trading.tcg.application.card.domain.PokemonCardSkill
import jakarta.persistence.*

@Entity
@Table(name = "pokemon_card_skill")
data class PokemonCardSkillEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = false)
    val card: PokemonCardEntity,

    @Column(name = "name")
    val name: String?,

    @Column(name = "type")
    val type: String?,

    @Column(name = "damage")
    val damage: String?,

    @Column(name = "description", nullable = false)
    val description: String?
) {
    fun toDomain(): PokemonCardSkill {
        return PokemonCardSkill(
            id = id,
            name = name,
            type = type,
            damage = damage,
            description = description
        )
    }
}
