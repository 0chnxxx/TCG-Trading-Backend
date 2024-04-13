package com.trading.tcg.global.dto

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDateTime
import kotlin.math.ceil

data class Response<T>(
    val serverTime: LocalDateTime,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val pageResult: PageResult? = null,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    var data: T? = null
) {
    companion object {
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
        val totalElements: Int,
        val isFirst: Boolean,
        val isLast: Boolean
    ) {
        companion object {
            fun of(count: Int, page: Int, size: Int): PageResult {
                return Response.PageResult(
                    page = page,
                    size = size,
                    totalPages = ceil(count.toDouble() / size).toInt(),
                    totalElements = count,
                    isFirst = page == 1,
                    isLast = (count - ((page - 1) * size)) <= size
                )
            }
        }
    }
}
