package com.trading.tcg.global

import org.springframework.http.HttpStatus
import java.time.LocalDateTime

data class Response<T>(
    val statusCode: Int,
    val statusName: String,
    val serverTime: LocalDateTime,
    var data: T? = null
) {
    companion object {
        @JvmStatic
        fun <T> of(httpStatus: HttpStatus, data: T): Response<T> {
            return Response(httpStatus.value(), httpStatus.name, LocalDateTime.now(), data)
        }
    }
}
