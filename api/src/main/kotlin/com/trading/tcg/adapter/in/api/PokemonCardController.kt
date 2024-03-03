package com.trading.tcg.adapter.`in`.api

import com.trading.tcg.domain.PokemonCard
import com.trading.tcg.global.Response
import com.trading.tcg.port.`in`.PokemonCardUseCase
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class PokemonCardController(
    private val pokemonCardUseCase: PokemonCardUseCase
) {

    @GetMapping("/cards")
    fun findCards(page: Int, size: Int): Response<List<PokemonCard>> {
        return Response.of(HttpStatus.OK, pokemonCardUseCase.findPokemonCards(page, size))
    }
}
