package com.trading.tcg.global.exception

interface ErrorCode {
    val statusCode: Int
    val errorCode: String
    val errorMessage: String
}
