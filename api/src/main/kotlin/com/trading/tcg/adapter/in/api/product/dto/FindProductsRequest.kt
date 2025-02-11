package com.trading.tcg.adapter.`in`.api.product.dto

import com.trading.tcg.application.product.dto.request.FindProductsQuery
import com.trading.tcg.global.dto.Provider
import com.trading.tcg.global.validation.SelfValidator
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern

data class FindProductsRequest(
    @field:Pattern(regexp = "^(bidPlacedTime|bidClosedTime|bidCount|dealCount|price)", message = "유효한 정렬 대상이 아닙니다.")
    val order: String?,

    @field:Pattern(regexp = "^(asc|desc)", message = "유효한 정렬 방식이 아닙니다.")
    val sort: String?,

    @field:Pattern(regexp = "^(pokemon|yugioh|digimon)", message = "유효한 탭이 아닙니다.")
    val tab: String?,

    val rank: List<String>?,

    val category: List<String>?,

    val type: List<String>?,

    val effect: List<String>?,

    val form: List<String>?,

    val species: List<String>?,

    val summonType: List<String>?,

    val regulationMark: List<String>?,

    val isExcludedNotBidProduct: Boolean?,

    val search: String?,

    @field:Min(value = 1, message = "페이지 번호가 범위를 벗어났습니다.")
    val page: Int?,

    @field:Min(value = 1, message = "페이지 크기가 범위를 벗어났습니다.")
    val size: Int?
): SelfValidator() {
    fun toQuery(provider: Provider): FindProductsQuery {
        validate()

        return FindProductsQuery(
            userId = provider.getUser()?.id ?: 0,
            order = order ?: "bidPlacedTime",
            sort = sort ?: "desc",
            tab = tab,
            rank = rank ?: emptyList(),
            category = category ?: emptyList(),
            type = type ?: emptyList(),
            effect = effect ?: emptyList(),
            form = form ?: emptyList(),
            species = species ?: emptyList(),
            summonType = summonType ?: emptyList(),
            regulationMark = regulationMark ?: emptyList(),
            isExcludedNotBidProduct = isExcludedNotBidProduct ?: false,
            search = search,
            page = page ?: 1,
            size = size ?: 10
        )
    }
}
