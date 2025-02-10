package com.trading.tcg.application.card.domain

class YugiohCard(
    code: String,
    name: String,
    image: String,
    val packs: List<YugiohCardPack>,
    val categories: List<String>,
    val type: String,
    val effect: String?,
    val species: String?,
    val summonType: String?,
    val summonValue: Int?,
    val marker: String?,
    val attack: String?,
    val defence: String?,
    val pendulumScale: Int?,
    val pendulumDescription: String?,
    val description: String?
): Card(code, name, image)
