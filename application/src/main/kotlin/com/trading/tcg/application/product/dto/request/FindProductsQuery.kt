package com.trading.tcg.application.product.dto.request

import com.trading.tcg.global.domain.SortBy
import com.trading.tcg.product.domain.ProductOrderBy
import com.trading.tcg.product.domain.ProductTab

data class FindProductsQuery(
    val userId: Long,
    val order: ProductOrderBy,
    val sort: SortBy,
    val tab: ProductTab?,
    val rank: List<String>,
    val category: List<String>,
    val type: List<String>,
    val effect: List<String>,
    val form: List<String>,
    val species: List<String>,
    val summonType: List<String>,
    val regulationMark: List<String>,
    val isBookmarked: Boolean?,
    val isExcludedNotBidProduct: Boolean?,
    val search: String?,
    val page: Int,
    val size: Int
)
