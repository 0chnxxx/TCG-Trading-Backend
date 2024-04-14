package com.trading.tcg.adapter.out.persistence.user.storage

import com.trading.tcg.adapter.out.persistence.card.entity.CardEntity
import com.trading.tcg.adapter.out.persistence.user.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserJpaRepository: JpaRepository<UserEntity, Long> {
    fun findByEmail(email: String): UserEntity?
}
