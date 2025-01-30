package com.trading.tcg.global.validation

import jakarta.validation.ConstraintViolationException
import jakarta.validation.Validation

open class SelfValidator {
    private val validator = Validation.buildDefaultValidatorFactory().validator

    init {
        validate()
    }

    private fun validate() {
        val violations = validator.validate(this)

        if (violations.isNotEmpty()) {
            throw ConstraintViolationException(violations)
        }
    }
}
