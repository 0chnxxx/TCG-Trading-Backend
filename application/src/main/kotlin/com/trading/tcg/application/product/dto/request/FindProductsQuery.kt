package com.trading.tcg.application.product.dto.request

data class FindProductsQuery(
    val userId: Long,
    val order: String,
    val sort: String,
    val tab: String?,
    val rank: List<String>,
    val category: List<String>,
    val type: List<String>,
    val effect: List<String>,
    val form: List<String>,
    val species: List<String>,
    val summonType: List<String>,
    val regulationMark: List<String>,
    val isExcludedNotBidProduct: Boolean,
    val search: String?,
    val page: Int,
    val size: Int
)
