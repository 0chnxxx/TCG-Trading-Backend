package com.trading.tcg.application.pokemon.domain

import java.time.LocalDateTime

class PokemonCard(
    val id: Long?,
    val pack: PokemonCardPack,
    val code: String,
    val serialCode: String?,
    val sequenceNumber: String?,
    val regulationMark: String?,
    val rank: PokemonCardRank?,
    val categories: List<PokemonCategory>,
    val type: String?,
    val name: String,
    val image: String,
    val illustrator: String?,
    val level: String?,
    val hp: String?,
    val weaknessType: String?,
    val weaknessValue: String?,
    val resistanceType: String?,
    val resistanceValue: String?,
    val retreatValue: Int?,
    val skills: List<PokemonCardSkill>,
    val createdTime: LocalDateTime,
    val updatedTime: LocalDateTime?
)
