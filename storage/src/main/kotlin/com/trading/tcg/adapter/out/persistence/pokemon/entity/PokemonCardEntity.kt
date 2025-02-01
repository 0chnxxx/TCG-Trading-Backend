package com.trading.tcg.adapter.out.persistence.pokemon.entity

import com.trading.tcg.adapter.out.persistence.global.BaseEntity
import com.trading.tcg.application.pokemon.domain.*
import jakarta.persistence.*

@Entity
@Table(name = "pokemon_card")
class PokemonCardEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    val pack: PokemonCardPackEntity,

    @Column(name = "code", nullable = false)
    val code: String,

    @Column(name = "serial_code", nullable = false)
    val serialCode: String? = null,

    @Column(name = "sequence_number", nullable = false)
    val sequenceNumber: String? = null,

    @Column(name = "regulation_mark")
    val regulationMark: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "rank")
    val rank: PokemonCardRank? = null,

    @Column(name = "name", nullable = false)
    val name: String,

    @OneToMany(mappedBy = "card", fetch = FetchType.LAZY)
    val categories: List<PokemonCardCategoryEntity>,

    @Column(name = "type", nullable = false)
    val type: String? = null,

    @Column(name = "image", nullable = false)
    val image: String,

    @Column(name = "illustrator")
    val illustrator: String? = null,

    @Column(name = "level", nullable = false)
    val level: String? = null,

    @Column(name = "hp", nullable = false)
    val hp: String? = null,

    @Column(name = "weakness_type")
    val weaknessType: String? = null,

    @Column(name = "weakness_value")
    val weaknessValue: String? = null,

    @Column(name = "resistance_type")
    val resistanceType: String? = null,

    @Column(name = "resistance_value")
    val resistanceValue: String? = null,

    @Column(name = "retreat_value")
    val retreatValue: Int? = null,

    @OneToMany(mappedBy = "card", fetch = FetchType.LAZY)
    val skills: List<PokemonCardSkillEntity>
) : BaseEntity() {

    fun toDomain(): PokemonCard {
        return PokemonCard(
            id = id,
            pack = pack.toDomain(),
            code = code,
            serialCode = serialCode,
            sequenceNumber = sequenceNumber,
            regulationMark = regulationMark,
            rank = rank,
            categories = categories.map {
                PokemonCategory.entries.find { value -> it.category.name == value.category }!!
            },
            type = type,
            name = name,
            image = image,
            illustrator = illustrator,
            level = level,
            hp = hp,
            weaknessType = weaknessType,
            weaknessValue = weaknessValue,
            resistanceType = resistanceType,
            resistanceValue = resistanceValue,
            retreatValue = retreatValue,
            skills = skills.map { it.toDomain() },
            createdTime = createdTime,
            updatedTime = updatedTime
        )
    }
}
