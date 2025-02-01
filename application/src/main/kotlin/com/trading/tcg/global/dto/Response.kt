package com.trading.tcg.global.dto

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDateTime
import kotlin.math.ceil

data class Response<T>(
    val serverTime: LocalDateTime,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val pageResult: PageResult? = null,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val data: T? = null
) {
    companion object {
        @JvmStatic
        fun <T> of(pageResult: PageResult? = null, data: T): Response<T> {
            return Response(
                LocalDateTime.now(),
                pageResult,
                data
            )
        }
    }

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
            fun of(totalCount: Long, page: Int, size: Int): PageResult {
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
}
