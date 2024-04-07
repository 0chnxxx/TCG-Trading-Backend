package com.trading.tcg.card.dto.response

import com.trading.tcg.domain.card.Card
import java.time.LocalDateTime

data class PokemonCardDetailDto(
    val id: Long? = null,
    val type: String,
    val code: String,
    val image: String,
    val imageIllustrator: String? = null,
    val serialCode: String,
    val regulationMark: String? = null,
    val rank: String? = null,
    val sequenceNumber: String,
    val categories: List<String?>,
    val packs: List<String>,
    val abilities: List<Ability>,
    val pokemon: Pokemon,
    val createdTime: LocalDateTime,
    val updatedTime: LocalDateTime? = null
) {
    data class Ability(
        val id: Long?,
        val types: List<String>,
        val name: String,
        val value: String?,
        val description: String?
    )

    data class Pokemon(
        val name: String,
        val info: String? = null,
        val description: String? = null,
        val level: Int? = null,
        val hp: Int,
        val type: String? = null,
        val weaknessType: String? = null,
        val weaknessValue: String? = null,
        val resistanceType: String? = null,
        val resistanceValue: String? = null,
        val retreatValue: Int? = null,
    )

    companion object {
        fun ofDomain(card: Card): PokemonCardDetailDto {
            return PokemonCardDetailDto(
                id = card.id,
                type = card.type.name,
                code = card.code,
                image = card.image,
                imageIllustrator = card.imageIllustrator,
                serialCode = card.serialCode.name,
                regulationMark = card.regulationMark?.name,
                rank = card.rank?.name,
                packs = card.packs.map { it.name },
                sequenceNumber = card.sequenceNumber,
                categories = card.categories.map { it?.category },
                abilities = card.abilities.map { Ability(it.id, it.types, it.name, it.value, it.description) },
                pokemon = Pokemon(
                    card.pokemon.name,
                    card.pokemon.info,
                    card.pokemon.description,
                    card.pokemon.level,
                    card.pokemon.hp,
                    card.pokemon.type?.name,
                    card.pokemon.weaknessType?.name,
                    card.pokemon.weaknessValue,
                    card.pokemon.resistanceType?.name,
                    card.pokemon.resistanceValue,
                    card.pokemon.retreatValue
                ),
                createdTime = card.createdTime,
                updatedTime = card.updatedTime
            )
        }
    }
}
