package com.trading.tcg.global.util

import java.math.BigDecimal
import java.math.RoundingMode
import java.text.DecimalFormat

object BigDecimalUtil {
    fun BigDecimal.toDisplayString(): String {
        val rounded = this.setScale(0, RoundingMode.DOWN)
        val formatter = DecimalFormat("#,###")

        return formatter.format(rounded) + "원"
    }
}
