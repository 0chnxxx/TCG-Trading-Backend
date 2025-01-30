package com.trading.tcg.adapter.out.persistence.card.entity

import com.trading.tcg.adapter.out.persistence.global.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "pokemon_card_skill")
data class PokemonCardSkillEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id", nullable = false)
    val card: PokemonCardEntity,

    @Column(name = "name")
    val name: String? = null,

    @Column(name = "type")
    val type: String? = null,

    @Column(name = "damage")
    val damage: String? = null,

    @Column(name = "description", nullable = false)
    val description: String? = null
) : BaseEntity() {
}
