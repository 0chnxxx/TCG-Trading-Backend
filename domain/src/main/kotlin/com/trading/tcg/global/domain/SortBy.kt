package com.trading.tcg.global.domain

import com.trading.tcg.global.exception.CustomException
import com.trading.tcg.global.exception.ServiceErrorCode
import com.trading.tcg.product.domain.ProductOrderBy

enum class SortBy(
    val queryName: String,
    val displayName: String
) {
    ASC("asc", "오름차순"),
    DESC("desc", "내림차순");

    companion object {
        fun ofQuery(queryName: String): SortBy {
            return SortBy.entries.find { it.queryName.uppercase() == queryName.uppercase() }
                ?: throw CustomException(ServiceErrorCode.INVALID_SORT)
        }
    }
}
