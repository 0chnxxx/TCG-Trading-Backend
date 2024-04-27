package com.trading.tcg.application.user.port.out

import com.trading.tcg.application.user.domain.User

interface UserPersistencePort {
    fun findByEmail(email: String): User?
    fun save(user: User): User
}
