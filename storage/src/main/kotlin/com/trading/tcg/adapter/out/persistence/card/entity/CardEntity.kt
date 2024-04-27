package com.trading.tcg.adapter.out.persistence.card.entity

import com.trading.tcg.adapter.out.persistence.global.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "card")
class CardEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    val type: com.trading.tcg.application.card.domain.CardType,

    @Column(name = "code", nullable = false)
    val code: String,

    @Column(name = "image", nullable = false)
    val image: String,

    @Column(name = "image_illustrator")
    val imageIllustrator: String? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "serial_code", nullable = false)
    val serialCode: com.trading.tcg.application.card.domain.SerialCode,

    @Enumerated(EnumType.STRING)
    @Column(name = "regulation_mark")
    val regulationMark: com.trading.tcg.application.card.domain.RegulationMark? = null,

    @Enumerated(EnumType.STRING)
    @Column(name = "rank")
    val rank: com.trading.tcg.application.card.domain.Rank? = null,

    @Column(name = "sequence_number", nullable = false)
    val sequenceNumber: String,

    @OneToMany(mappedBy = "card", fetch = FetchType.LAZY)
    val packs: List<CardPackEntity>,

    @OneToMany(mappedBy = "card", fetch = FetchType.LAZY)
    val categories: List<CardCategoryEntity>,

    @OneToOne(fetch = FetchType.LAZY)
    val pokemon: PokemonEntity,

    @OneToMany(mappedBy = "card", fetch = FetchType.LAZY)
    val abilities: List<CardAbilityEntity>
) : BaseEntity() {

    fun toDomain(): com.trading.tcg.application.card.domain.Card {
        return com.trading.tcg.application.card.domain.Card(
            id = id,
            type = type,
            code = code,
            image = image,
            imageIllustrator = imageIllustrator,
            serialCode = serialCode,
            regulationMark = regulationMark,
            rank = rank,
            packs = packs.map { com.trading.tcg.application.card.domain.Pack(it.pack.id, it.pack.name) },
            sequenceNumber = sequenceNumber,
            categories = categories.map {
                com.trading.tcg.application.card.domain.Category.entries.find { c ->
                    c.category.equals(
                        it.category.name
                    )
                }
            }.toList(),
            pokemon = com.trading.tcg.application.card.domain.Pokemon(
                pokemon.id,
                pokemon.name,
                pokemon.info,
                pokemon.description,
                pokemon.level,
                pokemon.hp,
                pokemon.type,
                pokemon.weaknessType,
                pokemon.weaknessValue,
                pokemon.resistanceType,
                pokemon.resistanceValue,
                pokemon.retreatValue,
                pokemon.createdTime,
                pokemon.updatedTime
            ),
            abilities = abilities.map {
                com.trading.tcg.application.card.domain.CardAbility(
                    it.id,
                    it.type?.split("\n") ?: emptyList(),
                    it.name,
                    it.value,
                    it.description
                )
            },
            createdTime = createdTime,
            updatedTime = updatedTime
        )
    }
}
