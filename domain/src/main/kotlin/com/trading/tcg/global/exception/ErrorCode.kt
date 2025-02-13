package com.trading.tcg.global.exception

interface ErrorCode {
    fun getStatusCode(): Int
    fun getErrorCode(): String
    fun getErrorMessage(): String
}
