package com.trading.tcg.adapter.out.persistence.card.entity

import com.trading.tcg.adapter.out.persistence.global.BaseEntity
import com.trading.tcg.application.card.domain.*
import jakarta.persistence.*

@Entity
@Table(name = "pokemon_card")
class PokemonCardEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @Column(name = "code", nullable = false)
    val code: String,

    @ManyToOne(fetch = FetchType.LAZY)
    val pack: PokemonCardPackEntity,

    @Column(name = "serial_code", nullable = false)
    val serialCode: String? = null,

    @Column(name = "sequence_number", nullable = false)
    val sequenceNumber: String? = null,

    @Column(name = "name", nullable = false)
    val name: String,

    @OneToMany(mappedBy = "card", fetch = FetchType.LAZY)
    val categories: List<PokemonCardCategoryEntity>,

    @Column(name = "type", nullable = false)
    val type: String? = null,

    @Column(name = "level", nullable = false)
    val level: String? = null,

    @Column(name = "hp", nullable = false)
    val hp: String? = null,

    @Column(name = "image", nullable = false)
    val image: String,

    @Column(name = "illustrator")
    val illustrator: String? = null,

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

    @Column(name = "regulation_mark")
    val regulationMark: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "rank")
    val rank: Rank? = null,

    @OneToMany(mappedBy = "card", fetch = FetchType.LAZY)
    val skills: List<PokemonCardSkillEntity>
) : BaseEntity() {

    fun toDomain(): PokemonCard {
        val card = PokemonCard(
            id = id,
            code = code,
            pack = PokemonCardPack(
                id = pack.id,
                name = pack.name,
                image = pack.image
            ),
            serialCode = serialCode,
            sequenceNumber = sequenceNumber,
            name = name,
            categories = categories
                .map {
                    Category.entries.find { value -> it.category.name == value.category }!!
                }
                .toMutableList(),
            type = type,
            level = level,
            hp = hp,
            image = image,
            illustrator = illustrator,
            weaknessType = weaknessType,
            weaknessValue = weaknessValue,
            resistanceType = resistanceType,
            resistanceValue = resistanceValue,
            retreatValue = retreatValue,
            regulationMark = regulationMark,
            rank = rank,
            skills = mutableListOf(),
            createdTime = createdTime,
            updatedTime = updatedTime
        )

        skills
            .map {
                PokemonCardSkill(
                    id = it.id,
                    card = card,
                    name = it.name,
                    type = it.type,
                    damage = it.damage,
                    description = it.description
                )
            }
            .forEach {
                card.skills.add(it)
            }

        return card
    }
}
