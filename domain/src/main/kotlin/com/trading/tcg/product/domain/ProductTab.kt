package com.trading.tcg.product.domain

import com.trading.tcg.global.exception.CustomException
import com.trading.tcg.product.exception.ProductErrorCode

enum class ProductTab(
    val queryName: String,
    val displayName: String
) {
    POKEMON("pokemon", "포켓몬"),
    YUGIOH("yugioh", "유희왕"),
    DIGIMON("digimon", "디지몬");

    companion object {
        fun ofQuery(queryName: String): ProductTab {
            return ProductTab.entries.find { it.queryName.uppercase() == queryName.uppercase() }
                ?: throw CustomException(ProductErrorCode.INVALID_PRODUCT_TAB)
        }
    }
}
