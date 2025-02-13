package com.trading.tcg.global.exception

import java.lang.RuntimeException

class CustomException(
    val errorCode: ErrorCode
) : RuntimeException()
