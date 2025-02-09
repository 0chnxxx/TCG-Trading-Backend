package com.trading.tcg.application.card.domain

import java.time.LocalDate

data class YugiohCardPack(
    val id: Long?,
    val category: String,
    val name: String,
    val releaseDate: LocalDate
)
