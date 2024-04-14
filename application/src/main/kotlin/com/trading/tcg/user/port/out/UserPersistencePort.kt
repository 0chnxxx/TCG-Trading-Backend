package com.trading.tcg.user.port.out

import com.trading.tcg.user.domain.User

interface UserPersistencePort {
    fun findByEmail(email: String): User?
    fun save(user: User): User
}
