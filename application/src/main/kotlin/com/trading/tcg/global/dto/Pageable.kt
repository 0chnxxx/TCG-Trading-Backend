package com.trading.tcg.global.dto

import kotlin.math.ceil

data class Pageable<T>(
    val pageResult: PageResult,
    val data: T
) {
    data class PageResult(
        val page: Int,
        val size: Int,
        val totalPages: Int,
        val totalElements: Long,
        val isFirst: Boolean,
        val isLast: Boolean
    ) {
        companion object {
            @JvmStatic
            fun of(
                totalCount: Long,
                page: Int,
                size: Int
            ): PageResult {
                return PageResult(
                    page = page,
                    size = size,
                    totalPages = ceil(totalCount.toDouble() / size).toInt(),
                    totalElements = totalCount,
                    isFirst = page == 1,
                    isLast = (totalCount - ((page - 1) * size)) <= size
                )
            }
        }
    }

    companion object {
        @JvmStatic
        fun <T> of(
            pageResult: PageResult,
            data: T
        ): Pageable<T> {
            return Pageable(
                pageResult = pageResult,
                data = data
            )
        }
    }
}
