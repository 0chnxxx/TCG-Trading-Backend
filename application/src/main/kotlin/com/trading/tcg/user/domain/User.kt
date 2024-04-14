package com.trading.tcg.user.domain

import com.trading.tcg.user.port.out.UserPersistencePort
import java.lang.Exception
import java.time.LocalDateTime

class User(
    val id: Long? = null,
    val email: String,
    val password: String,
    val kakaoId: String? = null,
    val appleId: String? = null,
    val role: UserRole,
    val fcmToken: String? = null,
    val createdTime: LocalDateTime? = null,
    val updatedTime: LocalDateTime? = null
) {
    fun checkDuplicateEmail(userPersistencePort: UserPersistencePort) {
        if (userPersistencePort.findByEmail(this.email) != null) {
            throw Exception("duplicated email")
        }
    }
}
