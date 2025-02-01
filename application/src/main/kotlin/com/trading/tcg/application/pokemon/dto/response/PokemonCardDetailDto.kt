package com.trading.tcg.application.pokemon.dto.response

import com.trading.tcg.application.pokemon.domain.PokemonCard
import java.time.LocalDateTime

data class PokemonCardDetailDto(
    val id: Long? = null,
    val pack: Pack,
    val code: String,
    val serialCode: String? = null,
    val sequenceNumber: String? = null,
    val regulationMark: String? = null,
    val rank: String? = null,
    val categories: List<String>,
    val type: String? = null,
    val name: String,
    val image: String,
    val illustrator: String? = null,
    val level: String? = null,
    val hp: String? = null,
    val weaknessType: String? = null,
    val weaknessValue: String? = null,
    val resistanceType: String? = null,
    val resistanceValue: String? = null,
    val retreatValue: Int? = null,
    val skills: List<Skill>,
    val createdTime: LocalDateTime,
    val updatedTime: LocalDateTime? = null
) {
    data class Pack(
        val id: Long?,
        val name: String? = null
    )

    data class Skill(
        val id: Long? = null,
        val name: String? = null,
        val type: String? = null,
        val damage: String? = null,
        val description: String? = null
    )

    companion object {
        @JvmStatic
        fun ofDomain(card: PokemonCard): PokemonCardDetailDto {
            return PokemonCardDetailDto(
                id = card.id,
                pack = Pack(
                    card.pack.id,
                    card.pack.name
                ),
                code = card.code,
                serialCode = card.serialCode,
                sequenceNumber = card.sequenceNumber,
                regulationMark = card.regulationMark,
                rank = card.rank.let { it!!.rank },
                categories = card.categories.map { it.category },
                type = card.type,
                name = card.name,
                image = card.image,
                illustrator = card.illustrator,
                level = card.level,
                hp = card.hp,
                weaknessType = card.weaknessType,
                weaknessValue = card.weaknessValue,
                resistanceType = card.resistanceType,
                resistanceValue = card.resistanceValue,
                retreatValue = card.retreatValue,
                skills = card.skills.map {
                    Skill(
                        it.id,
                        it.name,
                        it.type,
                        it.damage,
                        it.description
                    )
                },
                createdTime = card.createdTime,
                updatedTime = card.updatedTime
            )
        }
    }
}
