package com.trading.tcg.adapter.out.persistence.user

import com.trading.tcg.application.user.port.out.UserPersistencePort
import com.trading.tcg.user.domain.User
import org.springframework.stereotype.Repository
import java.util.*

@Repository
class UserPersistenceAdapter(
    private val userJpaRepository: UserJpaRepository
): UserPersistencePort {
    override fun findById(id: Long): Optional<User> {
        return userJpaRepository.findById(id)
    }

    override fun findByEmail(email: String): Optional<User> {
        return userJpaRepository.findByEmail(email)
    }

    override fun save(user: User) {
        userJpaRepository.save(user)
    }

    override fun delete(user: User) {
        userJpaRepository.delete(user)
    }
}
