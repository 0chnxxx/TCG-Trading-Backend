package com.trading.tcg.card.service

import com.trading.tcg.card.dto.request.FindPokemonCardQuery
import com.trading.tcg.card.dto.response.PokemonCardDetailDto
import com.trading.tcg.card.dto.response.PokemonCardDto
import com.trading.tcg.card.port.`in`.FindPokemonCardUseCase
import com.trading.tcg.card.port.out.PokemonCardPersistencePort
import com.trading.tcg.global.dto.Response
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service

@Service
@RequiredArgsConstructor
class PokemonCardService(
    private val pokemonCardPersistencePort : PokemonCardPersistencePort
) : FindPokemonCardUseCase {
    override fun findPokemonCards(query: FindPokemonCardQuery): Response<List<PokemonCardDto>> {
        val pokemonCards = pokemonCardPersistencePort.findPokemonCards(query)

        return Response.of(
            pageResult = Response.PageResult.of(
                pokemonCards.size,
                query.page,
                query.size
            ),
            data = pokemonCards.stream()
                .map { PokemonCardDto.ofDomain(it) }
                .toList()
        )
    }

    override fun findPokemonCard(cardId: Long): Response<PokemonCardDetailDto> {
        val pokemonCard = pokemonCardPersistencePort.findPokemonCard(cardId)

        return Response.of(
            data = PokemonCardDetailDto.ofDomain(pokemonCard)
        )
    }
}
