package com.trading.tcg.adapter.`in`.api.product.dto

import com.trading.tcg.application.product.dto.request.FindProductsQuery
import com.trading.tcg.global.dto.SortBy
import com.trading.tcg.global.dto.Provider
import com.trading.tcg.global.exception.CustomException
import com.trading.tcg.global.exception.RequestErrorCode
import com.trading.tcg.application.product.dto.common.ProductField
import com.trading.tcg.product.domain.ProductCategory

data class FindProductsRequest(
    val order: String?,
    val sort: String?,
    val tab: String?,
    val rank: List<String>?,
    val category: List<String>?,
    val type: List<String>?,
    val effect: List<String>?,
    val form: List<String>?,
    val species: List<String>?,
    val summonType: List<String>?,
    val regulationMark: List<String>?,
    val isBookmarked: Boolean?,
    val isExcludedNotBidProduct: Boolean?,
    val search: String?,
    val page: Int?,
    val size: Int?
) {
    fun toQuery(provider: Provider): FindProductsQuery {
        return FindProductsQuery(
            userId = provider.getUser()?.id ?: 0,
            order = order?.let { ProductField.ofQuery(it) } ?: ProductField.BID_PLACED_TIME,
            sort = sort?.let { SortBy.ofQuery(it) } ?: SortBy.DESC,
            tab = tab.let { ProductCategory.ofQuery(tab!!) },
            rank = rank ?: emptyList(),
            category = category ?: emptyList(),
            type = type ?: emptyList(),
            effect = effect ?: emptyList(),
            form = form ?: emptyList(),
            species = species ?: emptyList(),
            summonType = summonType ?: emptyList(),
            regulationMark = regulationMark ?: emptyList(),
            isBookmarked = isBookmarked,
            isExcludedNotBidProduct = isExcludedNotBidProduct,
            search = search,
            page = page?.also { if (it < 1) throw CustomException(RequestErrorCode.OUT_OF_PAGE) } ?: 1,
            size = size?.also { if (it < 1) throw CustomException(RequestErrorCode.OUT_OF_SIZE) } ?: 10
        )
    }
}
