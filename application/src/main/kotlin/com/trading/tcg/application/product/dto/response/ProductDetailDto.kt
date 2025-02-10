package com.trading.tcg.application.product.dto.response

import java.time.LocalDateTime

data class ProductDetailDto(
    val id: Long,
    val packs: List<String>,
    val name: String,
    val image: String,
    val code: String,
    val rank: String?,
    val category: List<String>,
    val type: List<String>,
    val form: List<String>,
    val isBookmarked: Boolean,
    val createdTime: LocalDateTime,
    val updatedTime: LocalDateTime?
) {
    constructor(
        id: Long,
        packs: List<String>,
        name: String,
        image: String,
        code: String,
        rank: String?,
        category: String?,
        type: String?,
        form: String?,
        isBookmarked: Boolean,
        createdTime: LocalDateTime,
        updatedTime: LocalDateTime?
    ): this(
        id = id,
        packs = packs,
        name = name,
        image = image,
        code = code,
        rank = rank ?: "-",
        category = category?.split("\n") ?: emptyList(),
        type = type?.split("\n") ?: emptyList(),
        form = form?.split("\n") ?: emptyList(),
        isBookmarked = isBookmarked,
        createdTime = createdTime,
        updatedTime = updatedTime
    )
}
