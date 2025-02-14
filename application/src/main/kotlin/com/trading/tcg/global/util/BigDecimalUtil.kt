package com.trading.tcg.global.util

import java.math.BigDecimal
import java.math.RoundingMode

object BigDecimalUtil {
    fun BigDecimal.toDisplayString(): String {
        return this.setScale(0, RoundingMode.DOWN).toString()
    }
}
