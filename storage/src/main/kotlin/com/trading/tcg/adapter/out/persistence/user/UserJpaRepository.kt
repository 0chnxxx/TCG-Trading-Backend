package com.trading.tcg.adapter.out.persistence.user

import com.trading.tcg.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserJpaRepository : JpaRepository<User, Long> {
    fun findByEmail(email: String): Optional<User>
}
