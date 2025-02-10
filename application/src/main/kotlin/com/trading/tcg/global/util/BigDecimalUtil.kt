package com.trading.tcg.global.util

import java.math.BigDecimal

fun BigDecimal.toDisplayString(): String {
    return this.setScale(0).toString()
}
