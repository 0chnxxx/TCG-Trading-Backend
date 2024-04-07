package com.trading.tcg.adapter.`in`.api.card

import com.trading.tcg.card.dto.request.FindPokemonCardQuery
import com.trading.tcg.card.dto.response.PokemonCardDetailDto
import com.trading.tcg.card.dto.response.PokemonCardDto
import com.trading.tcg.global.dto.Response
import com.trading.tcg.card.port.`in`.FindPokemonCardUseCase
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@Tag(name = "포켓몬 카드")
@RestController
class PokemonCardController(
    private val findPokemonCardUseCase: FindPokemonCardUseCase
) {
    @Operation(summary = "포켓몬 카드 리스트 조회", description = "포켓몬 카드 리스트를 조회한다.")
    @GetMapping("/cards")
    fun findCards(
        page: Int,
        size: Int
    ): Response<List<PokemonCardDto>> {
        val query = FindPokemonCardQuery(
            page,
            size
        )
        return Response.of(HttpStatus.OK, findPokemonCardUseCase.findPokemonCards(query))
    }

    @Operation(summary = "포켓몬 카드 단건 조회", description = "포켓몬 카드 단건을 조회한다.")
    @GetMapping("/cards/{cardId}")
    fun findCard(
        @PathVariable(name = "cardId") cardId: Long
    ): Response<PokemonCardDetailDto> {
        return Response.of(HttpStatus.OK, findPokemonCardUseCase.findPokemonCard(cardId))
    }
}
