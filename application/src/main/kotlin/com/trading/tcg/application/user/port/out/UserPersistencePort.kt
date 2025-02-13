package com.trading.tcg.application.user.port.out

import com.trading.tcg.user.domain.User
import java.util.Optional

interface UserPersistencePort {
    fun findById(id: Long): Optional<User>
    fun findByEmail(email: String): Optional<User>
    fun save(user: User)
    fun delete(user: User)
}
