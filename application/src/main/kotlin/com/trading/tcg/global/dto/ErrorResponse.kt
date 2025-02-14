package com.trading.tcg.global.dto

import java.time.LocalDateTime

data class ErrorResponse(
    val serverTime: LocalDateTime = LocalDateTime.now(),
    val path: String,
    val errorCode: String,
    val errorMessage: String
) {
    companion object {
        @JvmStatic
        fun of(path: String, errorCode: String, errorMessage: String): ErrorResponse {
            return ErrorResponse(
                serverTime = LocalDateTime.now(),
                path = path,
                errorCode = errorCode,
                errorMessage = errorMessage
            )
        }
    }
}
