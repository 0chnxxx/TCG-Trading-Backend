package com.trading.tcg.application.card.domain

import java.time.LocalDateTime

class PokemonCard(
    val id: Long?,
    val code: String,
    val pack: PokemonCardPack,
    val serialCode: String?,
    val sequenceNumber: String?,
    val name: String,
    val categories: MutableList<Category>,
    val type: String?,
    val level: String?,
    val hp: String?,
    val image: String,
    val illustrator: String?,
    val weaknessType: String?,
    val weaknessValue: String?,
    val resistanceType: String?,
    val resistanceValue: String?,
    val retreatValue: Int?,
    val regulationMark: String?,
    val rank: Rank?,
    val skills: MutableList<PokemonCardSkill>,
    val createdTime: LocalDateTime,
    val updatedTime: LocalDateTime?
)
