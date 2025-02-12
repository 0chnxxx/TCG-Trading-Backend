package com.trading.tcg.adapter.out.persistence.global.util

import com.querydsl.core.types.dsl.BooleanExpression

object ExpressionUtil {
    @JvmStatic
    fun <T> orAll(
        values: List<T>,
        predicate: (T) -> BooleanExpression
    ): BooleanExpression? {
        val combinedExpression = values
            .map(predicate)
            .reduceOrNull { acc, expression -> acc.or(expression) }

        return combinedExpression
    }

}
