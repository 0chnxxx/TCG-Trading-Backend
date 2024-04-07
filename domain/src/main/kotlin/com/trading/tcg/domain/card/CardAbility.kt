package com.trading.tcg.domain.card

data class CardAbility(
    val id: Long?,
    val types: List<String>,
    val name: String,
    val value: String?,
    val description: String?
)
