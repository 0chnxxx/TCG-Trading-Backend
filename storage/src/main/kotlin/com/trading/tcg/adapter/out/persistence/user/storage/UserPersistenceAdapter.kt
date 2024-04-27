package com.trading.tcg.adapter.out.persistence.user.storage

import com.trading.tcg.adapter.out.persistence.user.entity.UserEntity
import com.trading.tcg.application.user.domain.User
import com.trading.tcg.application.user.port.out.UserPersistencePort
import org.springframework.stereotype.Repository

@Repository
class UserPersistenceAdapter(
    private val userJpaRepository: UserJpaRepository
) : UserPersistencePort {
    override fun findByEmail(email: String): User? {
        val user = userJpaRepository.findByEmail(email)
        return user?.toDomain()
    }

    override fun save(user: User): User {
        val entity = UserEntity.ofDomain(user)
        userJpaRepository.save(entity)
        return entity.toDomain()
    }
}
