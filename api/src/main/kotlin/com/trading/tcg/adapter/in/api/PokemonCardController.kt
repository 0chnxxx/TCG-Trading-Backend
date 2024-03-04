package com.trading.tcg.adapter.`in`.api

import com.trading.tcg.domain.PokemonCard
import com.trading.tcg.global.dto.Response
import com.trading.tcg.port.`in`.PokemonCardUseCase
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "포켓몬 카드")
@RestController
class PokemonCardController(
    private val pokemonCardUseCase: PokemonCardUseCase
) {

    @Operation(summary = "포켓몬 카드 리스트 조회", description = "포켓몬 카드 리스트를 조회한다.")
    @GetMapping("/cards")
    fun findCards(page: Int, size: Int): Response<List<PokemonCard>> {
        return Response.of(HttpStatus.OK, pokemonCardUseCase.findPokemonCards(page, size))
    }
}
