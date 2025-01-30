package com.trading.tcg.adapter.`in`.api.card.controller

import com.trading.tcg.adapter.`in`.api.card.dto.FindPokemonCardsRequest
import com.trading.tcg.application.card.dto.response.PokemonCardDetailDto
import com.trading.tcg.application.card.dto.response.PokemonCardDto
import com.trading.tcg.application.card.port.`in`.FindPokemonCardUseCase
import com.trading.tcg.global.dto.Provider
import com.trading.tcg.global.dto.Response
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@Tag(name = "포켓몬 카드")
@RestController
class PokemonCardController(
    private val findPokemonCardUseCase: FindPokemonCardUseCase
) {
    @Operation(summary = "포켓몬 카드 리스트 조회", description = "포켓몬 카드 리스트를 조회한다.")
    @GetMapping("/cards")
    fun findPokemonCards(
        @AuthenticationPrincipal
        provider: Provider,
        @Parameter(name = "page", description = "페이지 번호", example = "1", required = true)
        @RequestParam(name = "page", required = true)
        page: Int?,
        @Parameter(name = "size", description = "페이지 크기", example = "10", required = true)
        @RequestParam(name = "size", required = true)
        size: Int?
    ): ResponseEntity<Response<List<PokemonCardDto>>> {
        val request = FindPokemonCardsRequest(
            page = page,
            size = size
        )

        return ResponseEntity(findPokemonCardUseCase.findPokemonCards(provider, request.toQuery()), HttpStatus.OK)
    }

    @Operation(summary = "포켓몬 카드 단건 조회", description = "포켓몬 카드 단건을 조회한다.")
    @PreAuthorize("hasRole('GUEST')")
    @GetMapping("/cards/{cardId}")
    fun findPokemonCard(
        @AuthenticationPrincipal
        provider: Provider,
        @Parameter(name = "cardId", description = "카드 번호", example = "1", required = true)
        @PathVariable(name = "cardId")
        cardId: Long
    ): ResponseEntity<Response<PokemonCardDetailDto>> {
        return ResponseEntity(findPokemonCardUseCase.findPokemonCard(provider, cardId), HttpStatus.OK)
    }
}
