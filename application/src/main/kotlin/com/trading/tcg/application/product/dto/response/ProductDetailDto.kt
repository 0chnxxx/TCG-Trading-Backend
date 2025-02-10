package com.trading.tcg.application.product.dto.response

import com.trading.tcg.global.util.toDisplayString
import java.math.BigDecimal
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
    val directBuyPrice: String,
    val directBuyQuantity: Int,
    val directSellPrice: String,
    val directSellQuantity: Int,
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
        maxBuyPrice: BigDecimal?,
        maxBuyQuantity: Int?,
        minSellPrice: BigDecimal?,
        minSellQuantity: Int?,
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
        directBuyPrice = minSellPrice?.toDisplayString() ?: "-",
        directBuyQuantity = minSellQuantity ?: 0,
        directSellPrice = maxBuyPrice?.toDisplayString() ?: "-",
        directSellQuantity = maxBuyQuantity ?: 0,
        isBookmarked = isBookmarked,
        createdTime = createdTime,
        updatedTime = updatedTime
    )
}
