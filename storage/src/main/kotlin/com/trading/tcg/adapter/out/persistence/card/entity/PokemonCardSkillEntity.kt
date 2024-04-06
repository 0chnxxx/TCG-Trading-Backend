package com.trading.tcg.adapter.out.persistence.card.entity

import com.trading.tcg.domain.card.PokemonCardSkill
import jakarta.persistence.*

@Entity
@Table(name = "pokemon_card_skill")
class PokemonCardSkillEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = false)
    private val card: PokemonCardEntity,

    @Column(name = "type", nullable = false)
    private val type: String,

    @Column(name = "name", nullable = false)
    private val name: String,

    @Column(name = "value")
    private val value: String? = null,

    @Column(name = "description", nullable = false)
    private val description: String) {

    fun toDomain(): PokemonCardSkill {
        return PokemonCardSkill(
            id = id,
            types = type.split("\n"),
            name = name,
            value = value.toString(),
            description = description
        )
    }
}
