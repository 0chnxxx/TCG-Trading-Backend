package com.trading.tcg.adapter.out.persistence.card.entity

import com.trading.tcg.adapter.out.persistence.global.BaseEntity
import com.trading.tcg.domain.card.PokemonCard
import jakarta.persistence.*

@Entity
@Table(name = "pokemon_card")
class PokemonCardEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private val id: Long? = null,

    @Column(name = "card_code", nullable = false)
    private val cardCode: String,

    @Column(name = "card_image", nullable = false)
    private val cardImage: String,

    @Column(name = "card_image_illustrator")
    private val cardImageIllustrator: String? = null,

    @Column(name = "card_serial_code", nullable = false)
    private val cardSerialCode: String,

    @Column(name = "card_regulation_mark")
    private val cardRegulationMark: String? = null,

    @Column(name = "card_rank")
    private val cardRank: String? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_pack_id", nullable = false)
    private val cardPack: PokemonCardPackEntity,

    @Column(name = "card_sequence_number", nullable = false)
    private val cardSequenceNumber: String,

    @Column(name = "pokemon_category", nullable = false)
    private val pokemonCategory: String,

    @Column(name = "pokemon_name", nullable = false)
    private val pokemonName: String,

    @Column(name = "pokemon_info")
    private val pokemonInfo: String? = null,

    @Column(name = "pokemon_description", columnDefinition = "text")
    private val pokemonDescription: String? = null,

    @Column(name = "pokemon_level")
    private val pokemonLevel: Int? = null,

    @Column(name = "pokemon_hp", nullable = false)
    private val pokemonHp: Int,

    @Column(name = "pokemon_type")
    private val pokemonType: String? = null,

    @OneToMany(mappedBy = "card", fetch = FetchType.LAZY)
    private val pokemonSkills: List<PokemonCardSkillEntity>,

    @Column(name = "pokemon_weakness_type")
    private val pokemonWeaknessType: String? = null,

    @Column(name = "pokemon_weakness_value")
    private val pokemonWeaknessValue: String? = null,

    @Column(name = "pokemon_resistance_type")
    private val pokemonResistanceType: String? = null,

    @Column(name = "pokemon_resistance_value")
    private val pokemonResistanceValue: String? = null,

    @Column(name = "pokemon_retreat_value")
    private val pokemonRetreatValue: Int? = null) : BaseEntity() {

    fun toDomain(): PokemonCard {
        return PokemonCard(
            id = id,
            cardCode = cardCode,
            cardImage = cardImage,
            cardImageIllustrator = cardImageIllustrator,
            cardSerialCode = cardSerialCode,
            cardRegulationMark = cardRegulationMark,
            cardRank = cardRank,
            cardPack = cardPack.toDomain(),
            cardSequenceNumber = cardSequenceNumber,
            pokemonCategory = pokemonCategory.split("\n"),
            pokemonName = pokemonName,
            pokemonInfo = pokemonInfo,
            pokemonDescription = pokemonDescription,
            pokemonLevel = pokemonLevel,
            pokemonHp = pokemonHp,
            pokemonType = pokemonType,
            pokemonSkills = pokemonSkills.map { it.toDomain() }.toList(),
            pokemonWeaknessType = pokemonWeaknessType,
            pokemonWeaknessValue = pokemonWeaknessValue,
            pokemonResistanceType = pokemonResistanceType,
            pokemonResistanceValue = pokemonResistanceValue,
            pokemonRetreatValue = pokemonRetreatValue,
            createdTime = createdTime,
            updatedTime = updatedTime
        )
    }
}
