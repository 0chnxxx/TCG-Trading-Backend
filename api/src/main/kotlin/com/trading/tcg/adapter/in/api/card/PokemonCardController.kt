package com.trading.tcg.adapter.`in`.api.card

import com.trading.tcg.card.dto.request.FindPokemonCardQuery
import com.trading.tcg.card.dto.response.PokemonCardDetailDto
import com.trading.tcg.card.dto.response.PokemonCardDto
import com.trading.tcg.global.dto.Response
import com.trading.tcg.card.port.`in`.FindPokemonCardUseCase
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpEntity
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
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
        @Parameter(name = "page", description = "페이지 번호", example = "1", required = true) page: Int,
        @Parameter(name = "size", description = "페이지 크기", example = "10", required = true) size: Int
    ): ResponseEntity<Response<List<PokemonCardDto>>> {
        val query = FindPokemonCardQuery(
            page,
            size
        )
        return ResponseEntity(findPokemonCardUseCase.findPokemonCards(query), HttpStatus.OK);
    }

    @Operation(summary = "포켓몬 카드 단건 조회", description = "포켓몬 카드 단건을 조회한다.")
    @GetMapping("/cards/{cardId}")
    fun findCard(
        @PathVariable(name = "cardId") cardId: Long
    ): ResponseEntity<Response<PokemonCardDetailDto>> {
        return ResponseEntity(findPokemonCardUseCase.findPokemonCard(cardId), HttpStatus.OK);
    }
}
