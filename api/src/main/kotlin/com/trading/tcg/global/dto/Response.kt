package com.trading.tcg.global.dto

import com.fasterxml.jackson.annotation.JsonInclude
import java.time.LocalDateTime

data class Response<T>(
    val serverTime: LocalDateTime,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val pageResult: Pageable.PageResult?,

    @JsonInclude(JsonInclude.Include.NON_NULL)
    val data: T?
) {
    companion object {
        @JvmStatic
        fun <T> of(
            pageResult: Pageable.PageResult? = null,
            data: T
        ): Response<T> {
            return Response(
                LocalDateTime.now(),
                pageResult,
                data
            )
        }
    }
}
