package com.trading.tcg.domain

data class PokemonCardSkill(
    val id: Long? = null,
    val types: List<String>,
    val name: String,
    val value: String,
    val description: String) {
}
