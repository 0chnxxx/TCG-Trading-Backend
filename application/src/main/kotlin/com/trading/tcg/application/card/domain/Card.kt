package com.trading.tcg.application.card.domain

import java.time.LocalDateTime

class Card(
    val id: Long?,
    val type: CardType,
    val code: String,
    val image: String,
    val imageIllustrator: String?,
    val serialCode: SerialCode,
    val regulationMark: RegulationMark?,
    val rank: Rank?,
    val sequenceNumber: String,
    val packs: List<Pack>,
    val categories: List<Category?>,
    val abilities: List<CardAbility>,
    val pokemon: Pokemon,
    val createdTime: LocalDateTime,
    val updatedTime: LocalDateTime?
)
