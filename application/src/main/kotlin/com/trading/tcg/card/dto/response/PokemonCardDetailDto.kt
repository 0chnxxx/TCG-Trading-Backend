package com.trading.tcg.card.dto.response

import com.trading.tcg.domain.card.PokemonCard
import com.trading.tcg.domain.card.PokemonCardPack
import com.trading.tcg.domain.card.PokemonCardSkill
import java.time.LocalDateTime

data class PokemonCardDetailDto(
    val id: Long? = null,
    val cardCode: String,
    val cardImage: String,
    val cardImageIllustrator: String? = null,
    val cardSerialCode: String,
    val cardRegulationMark: String? = null,
    val cardRank: String? = null,
    val cardPack: PokemonCardPack,
    val cardSequenceNumber: String,
    val pokemonCategory: List<String>,
    val pokemonName: String,
    val pokemonInfo: String? = null,
    val pokemonDescription: String? = null,
    val pokemonLevel: Int? = null,
    val pokemonHp: Int,
    val pokemonType: String? = null,
    val pokemonSkills: List<PokemonCardSkill>,
    val pokemonWeaknessType: String? = null,
    val pokemonWeaknessValue: String? = null,
    val pokemonResistanceType: String? = null,
    val pokemonResistanceValue: String? = null,
    val pokemonRetreatValue: Int? = null,
    val createdTime: LocalDateTime,
    val updatedTime: LocalDateTime? = null
) {
    companion object {
        fun ofDomain(pokemonCard: PokemonCard): PokemonCardDetailDto {
            return PokemonCardDetailDto(
                id = pokemonCard.id,
                cardCode = pokemonCard.cardCode,
                cardImage = pokemonCard.cardImage,
                cardImageIllustrator = pokemonCard.cardImageIllustrator,
                cardSerialCode = pokemonCard.cardSerialCode,
                cardRegulationMark = pokemonCard.cardRegulationMark,
                cardRank = pokemonCard.cardRank,
                cardPack = pokemonCard.cardPack,
                cardSequenceNumber = pokemonCard.cardSequenceNumber,
                pokemonCategory = pokemonCard.pokemonCategory,
                pokemonName = pokemonCard.pokemonName,
                pokemonInfo = pokemonCard.pokemonInfo,
                pokemonDescription = pokemonCard.pokemonDescription,
                pokemonLevel = pokemonCard.pokemonLevel,
                pokemonHp = pokemonCard.pokemonHp,
                pokemonType = pokemonCard.pokemonType,
                pokemonSkills = pokemonCard.pokemonSkills,
                pokemonWeaknessType = pokemonCard.pokemonWeaknessType,
                pokemonWeaknessValue = pokemonCard.pokemonWeaknessValue,
                pokemonResistanceType = pokemonCard.pokemonResistanceType,
                pokemonResistanceValue = pokemonCard.pokemonResistanceValue,
                pokemonRetreatValue = pokemonCard.pokemonRetreatValue,
                createdTime = pokemonCard.createdTime,
                updatedTime = pokemonCard.updatedTime
            )
        }
    }
}
