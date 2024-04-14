package com.trading.tcg.card.service

import com.trading.tcg.card.dto.request.FindPokemonCardQuery
import com.trading.tcg.card.dto.response.PokemonCardDetailDto
import com.trading.tcg.card.dto.response.PokemonCardDto
import com.trading.tcg.card.port.`in`.FindPokemonCardUseCase
import com.trading.tcg.card.port.out.PokemonCardPersistencePort
import com.trading.tcg.global.dto.Response
import com.trading.tcg.global.exception.CustomException
import com.trading.tcg.global.exception.ServiceErrorCode
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
@RequiredArgsConstructor
class PokemonCardService(
    private val pokemonCardPersistencePort : PokemonCardPersistencePort
) : FindPokemonCardUseCase {
    @Transactional(readOnly = true)
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

    @Transactional(readOnly = true)
    override fun findPokemonCard(cardId: Long): Response<PokemonCardDetailDto> {
        val pokemonCard = pokemonCardPersistencePort.findPokemonCard(cardId).orElseThrow { CustomException(ServiceErrorCode.NOT_FOUND_CARD) }

        return Response.of(
            data = PokemonCardDetailDto.ofDomain(pokemonCard)
        )
    }
}
