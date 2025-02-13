package com.trading.tcg.global.validation

import jakarta.validation.ConstraintViolationException
import jakarta.validation.Validation

open class SelfValidator {
    private val validator = Validation.buildDefaultValidatorFactory().validator

    fun validate() {
        val groups = arrayOf(ValidationGroup.First::class.java, ValidationGroup.Second::class.java)
        val violations = validator.validate(this, *groups)

        if (violations.isNotEmpty()) {
            throw ConstraintViolationException(violations)
        }
    }
}
