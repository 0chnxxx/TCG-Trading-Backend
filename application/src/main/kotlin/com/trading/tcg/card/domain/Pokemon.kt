package com.trading.tcg.card.domain

import java.time.LocalDateTime

data class Pokemon(
    val id: Long?,
    val name: String,
    val info: String?,
    val description: String?,
    val level: Int?,
    val hp: Int,
    val type: PokemonType?,
    val weaknessType: PokemonType?,
    val weaknessValue: String?,
    val resistanceType: PokemonType?,
    val resistanceValue: String?,
    val retreatValue: Int?,
    val createdTime: LocalDateTime,
    val updatedTime: LocalDateTime?
) {
    companion object {
        @JvmStatic
        fun Pokemon(id: Long?, name: String, info: String?, description: String?, level: Int?, hp: Int, type: PokemonType?, weaknessType: PokemonType?, weaknessValue: String?, resistanceType: PokemonType?, resistanceValue: String?, retreatValue: Int?, createdTime: LocalDateTime, updatedTime: LocalDateTime?): Pokemon {
            return Pokemon(
                id,
                name,
                info,
                description,
                level,
                hp,
                type,
                weaknessType,
                weaknessValue,
                resistanceType,
                resistanceValue,
                retreatValue,
                createdTime,
                updatedTime
            )
        }
    }
}
