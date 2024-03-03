package com.trading.tcg.domain

import java.time.LocalDateTime

data class PokemonCard(
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
    val updatedTime: LocalDateTime? = null) {
}
