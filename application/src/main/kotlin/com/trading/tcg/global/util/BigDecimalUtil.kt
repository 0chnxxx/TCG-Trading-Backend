package com.trading.tcg.global.util

import java.math.BigDecimal

object BigDecimalUtil {
    fun BigDecimal.toDisplayString(): String {
        return this.setScale(0).toString()
    }
}
