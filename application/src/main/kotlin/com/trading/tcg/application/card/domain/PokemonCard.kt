package com.trading.tcg.application.card.domain

class PokemonCard(
    code: String,
    name: String,
    image: String,
    val packs: List<PokemonCardPack>,
    val categories: List<String>,
    val type: String?,
    val serialCode: String?,
    val sequenceNumber: String?,
    val regulationMark: String?,
    val rank: String?,
    val level: String?,
    val hp: String?,
    val weaknessType: String?,
    val weaknessValue: String?,
    val resistanceType: String?,
    val resistanceValue: String?,
    val retreatValue: Int?,
    val skills: List<PokemonCardSkill>
): Card(code, name, image)
