package com.trading.tcg.application.user.domain

import com.trading.tcg.application.user.port.out.UserPersistencePort
import com.trading.tcg.global.exception.CustomException
import com.trading.tcg.global.exception.ServiceErrorCode
import java.time.LocalDateTime

class User(
    val id: Long? = null,
    val email: String,
    val password: String,
    val role: UserRole,
    val createdTime: LocalDateTime? = null,
    val updatedTime: LocalDateTime? = null
) {
    fun checkDuplicateEmail(userPersistencePort: UserPersistencePort) {
        val user = userPersistencePort.findByEmail(this.email)

        if (user != null) {
            throw CustomException(ServiceErrorCode.DUPLICATED_EMAIL)
        }
    }
}
