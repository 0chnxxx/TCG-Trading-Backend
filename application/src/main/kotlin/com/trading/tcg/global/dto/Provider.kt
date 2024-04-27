package com.trading.tcg.global.dto

import com.trading.tcg.application.user.domain.User

interface Provider {
    fun getUser(): User?
}
