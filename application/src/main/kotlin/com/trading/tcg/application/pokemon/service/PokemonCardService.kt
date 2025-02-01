package com.trading.tcg.application.pokemon.service

import com.trading.tcg.application.pokemon.dto.request.FindPokemonCardsQuery
import com.trading.tcg.application.pokemon.dto.response.PokemonCardDetailDto
import com.trading.tcg.application.pokemon.dto.response.PokemonCardDto
import com.trading.tcg.application.pokemon.port.`in`.FindPokemonCardUseCase
import com.trading.tcg.application.pokemon.port.out.PokemonCardPersistencePort
import com.trading.tcg.global.dto.Provider
import com.trading.tcg.global.dto.Response
import com.trading.tcg.global.exception.CustomException
import com.trading.tcg.global.exception.ServiceErrorCode
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@RequiredArgsConstructor
class PokemonCardService(
    private val pokemonCardPersistencePort: PokemonCardPersistencePort
) : FindPokemonCardUseCase {
    @Transactional(readOnly = true)
    override fun findPokemonCards(
        provider: Provider,
        query: FindPokemonCardsQuery
    ): Response<List<PokemonCardDto>> {
        val totalCount = pokemonCardPersistencePort.countPokemonCards()
        val pokemonCards = pokemonCardPersistencePort.findPokemonCards(query)

        return Response.of(
            pageResult = Response.PageResult.of(
                totalCount = totalCount,
                page = query.page,
                size = query.size
            ),
            data = pokemonCards.stream()
                .map { PokemonCardDto.ofDomain(it) }
                .toList()
        )
    }

    @Transactional(readOnly = true)
    override fun findPokemonCard(
        provider: Provider,
        cardId: Long
    ): Response<PokemonCardDetailDto> {
        val pokemonCard = pokemonCardPersistencePort.findPokemonCard(cardId)
            .orElseThrow { CustomException(ServiceErrorCode.NOT_FOUND_CARD) }

        return Response.of(
            data = PokemonCardDetailDto.ofDomain(pokemonCard)
        )
    }
}
