package com.trading.tcg.adapter.out.persistence.card.entity

import com.trading.tcg.adapter.out.persistence.global.BaseEntity
import com.trading.tcg.domain.card.Card
import com.trading.tcg.domain.card.PokemonType
import jakarta.persistence.*
import lombok.Getter

@Entity
@Table(name = "pokemon")
class PokemonEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @Column(name = "name", nullable = false)
    val name: String,

    @Column(name = "info")
    val info: String? = null,

    @Column(name = "description", columnDefinition = "text")
    val description: String? = null,

    @Column(name = "level")
    val level: Int? = null,

    @Column(name = "hp", nullable = false)
    val hp: Int,

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    val type: PokemonType? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "weakness_type")
    val weaknessType: PokemonType? = null,

    @Column(name = "weakness_value")
    val weaknessValue: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "resistance_type")
    val resistanceType: PokemonType? = null,

    @Column(name = "resistance_value")
    val resistanceValue: String? = null,

    @Column(name = "retreat_value")
    val retreatValue: Int? = null) : BaseEntity() {
}
