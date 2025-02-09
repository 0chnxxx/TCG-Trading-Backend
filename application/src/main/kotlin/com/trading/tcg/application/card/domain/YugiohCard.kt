package com.trading.tcg.application.card.domain

class YugiohCard(
    code: String,
    name: String,
    image: String,
    val packs: List<YugiohCardPack>,
    val type: String,
    val effect: String?,
    val species: String?,
    val attributes: List<String>,
    val gradeType: String?,
    val grade: Int?,
    val marker: String?,
    val attack: String?,
    val defence: String?,
    val pendulumScale: Int?,
    val pendulumDescription: String?,
    val description: String?
): Card(code, name, image)
