package com.trading.tcg.application.product.dto.request

import com.trading.tcg.global.dto.SortBy
import com.trading.tcg.application.product.dto.common.ProductField
import com.trading.tcg.product.domain.ProductCategory

data class FindProductsQuery(
    val userId: Long,
    val order: ProductField,
    val sort: SortBy,
    val tab: ProductCategory?,
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
