package com.trading.tcg.adapter.`in`.api.pokemon.dto

import com.trading.tcg.application.pokemon.dto.request.FindPokemonCardsQuery
import com.trading.tcg.global.validation.SelfValidator
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull

data class FindPokemonCardsRequest(
    @field:NotNull(message = "필수 파라미터가 입력되지 않았습니다.")
    @field:Min(value = 1, message = "페이지 번호가 범위를 벗어났습니다.")
    val page: Int?,

    @field:NotNull(message = "필수 파라미터가 입력되지 않았습니다.")
    @field:Min(value = 1, message = "페이지 크기가 범위를 벗어났습니다.")
    val size: Int?
): SelfValidator() {
    fun toQuery(): FindPokemonCardsQuery {
        return FindPokemonCardsQuery(
            page = page!!,
            size = size!!
        )
    }
}
