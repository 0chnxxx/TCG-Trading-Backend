package com.trading.tcg.domain.card

import java.time.LocalDateTime

class PokemonCard(
    val id: Long?,
    val cardCode: String,
    val cardImage: String,
    val cardImageIllustrator: String?,
    val cardSerialCode: String,
    val cardRegulationMark: String?,
    val cardRank: String?,
    val cardPack: PokemonCardPack,
    val cardSequenceNumber: String,
    val pokemonCategory: List<String>,
    val pokemonName: String,
    val pokemonInfo: String?,
    val pokemonDescription: String?,
    val pokemonLevel: Int?,
    val pokemonHp: Int,
    val pokemonType: String?,
    val pokemonSkills: List<PokemonCardSkill>,
    val pokemonWeaknessType: String?,
    val pokemonWeaknessValue: String?,
    val pokemonResistanceType: String?,
    val pokemonResistanceValue: String?,
    val pokemonRetreatValue: Int?,
    val createdTime: LocalDateTime,
    val updatedTime: LocalDateTime?
)
