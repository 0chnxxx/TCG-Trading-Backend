package com.trading.tcg.global.dto

import com.trading.tcg.user.domain.User

interface Provider {
    fun getUser(): User?
}
