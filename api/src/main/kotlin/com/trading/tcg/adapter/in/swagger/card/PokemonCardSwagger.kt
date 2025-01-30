package com.trading.tcg.adapter.`in`.swagger.card

import com.trading.tcg.application.card.dto.response.PokemonCardDetailDto
import com.trading.tcg.application.card.dto.response.PokemonCardDto
import com.trading.tcg.global.dto.Provider
import com.trading.tcg.global.dto.Response
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.ResponseEntity

@Tag(name = "포켓몬 카드")
interface PokemonCardSwagger {
    @Operation(summary = "포켓몬 카드 리스트 조회", description = "포켓몬 카드 리스트를 조회한다.")
    fun findPokemonCards(
        provider: Provider,
        @Parameter(name = "page", description = "페이지 번호", example = "1", required = true)
        page: Int?,
        @Parameter(name = "size", description = "페이지 크기", example = "10", required = true)
        size: Int?
    ): ResponseEntity<Response<List<PokemonCardDto>>>

    @Operation(summary = "포켓몬 카드 단건 조회", description = "포켓몬 카드 단건을 조회한다.")
    fun findPokemonCard(
        provider: Provider,
        @Parameter(name = "cardId", description = "카드 번호", example = "1", required = true)
        cardId: Long
    ): ResponseEntity<Response<PokemonCardDetailDto>>
}
