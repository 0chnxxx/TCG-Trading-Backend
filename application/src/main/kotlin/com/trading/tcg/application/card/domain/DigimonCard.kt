package com.trading.tcg.application.card.domain

class DigimonCard(
    code: String,
    name: String,
    image: String,
    val pack: DigimonCardPack,
    val rank: String,
    val category: String,
    val types: List<String>,
    val form: String?,
    val species: String?,
    val level: Int?,
    val dp: Int?,
    val appearanceCost: Int?,
    val evolutionCost1: String?,
    val evolutionCost2: String?,
    val topDescription: String?,
    val bottomDescription: String?
): Card(code, name, image)
