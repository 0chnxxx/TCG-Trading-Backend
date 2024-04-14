package com.trading.tcg.global.validation

import jakarta.validation.GroupSequence
import jakarta.validation.groups.Default

@GroupSequence(ValidationGroup.First::class, ValidationGroup.Second::class, Default::class)
interface ValidationSequence {
}
