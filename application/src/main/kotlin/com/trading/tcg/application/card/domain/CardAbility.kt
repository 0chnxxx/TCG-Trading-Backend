package com.trading.tcg.application.card.domain

data class CardAbility(
    val id: Long?,
    val types: List<String>,
    val name: String,
    val value: String?,
    val description: String?
)
