package com.trading.tcg.adapter.`in`.api.card.controller

import com.trading.tcg.adapter.`in`.api.card.dto.FindPokemonCardsRequest
import com.trading.tcg.adapter.`in`.swagger.card.PokemonCardSwagger
import com.trading.tcg.application.card.dto.response.PokemonCardDetailDto
import com.trading.tcg.application.card.dto.response.PokemonCardDto
import com.trading.tcg.application.card.port.`in`.FindPokemonCardUseCase
import com.trading.tcg.global.dto.Provider
import com.trading.tcg.global.dto.Response
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class PokemonCardController(
    private val findPokemonCardUseCase: FindPokemonCardUseCase
): PokemonCardSwagger {
    @GetMapping("/cards")
    override fun findPokemonCards(
        @AuthenticationPrincipal
        provider: Provider,
        @RequestParam(name = "page", required = true)
        page: Int?,
        @RequestParam(name = "size", required = true)
        size: Int?
    ): ResponseEntity<Response<List<PokemonCardDto>>> {
        val request = FindPokemonCardsRequest(
            page = page,
            size = size
        )

        return ResponseEntity(findPokemonCardUseCase.findPokemonCards(provider, request.toQuery()), HttpStatus.OK)
    }

    @PreAuthorize("hasRole('GUEST')")
    @GetMapping("/cards/{cardId}")
    override fun findPokemonCard(
        @AuthenticationPrincipal
        provider: Provider,
        @PathVariable(name = "cardId")
        cardId: Long
    ): ResponseEntity<Response<PokemonCardDetailDto>> {
        return ResponseEntity(findPokemonCardUseCase.findPokemonCard(provider, cardId), HttpStatus.OK)
    }
}
