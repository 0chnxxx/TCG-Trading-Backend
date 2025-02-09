package com.trading.tcg.adapter.out.persistence.user.entity

import com.trading.tcg.adapter.out.persistence.global.entity.BaseEntity
import com.trading.tcg.application.user.domain.User
import com.trading.tcg.application.user.domain.UserRole
import jakarta.persistence.*

@Entity
@Table(name = "user")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    val id: Long? = null,

    @Column(name = "email", nullable = false)
    val email: String,

    @Column(name = "password", nullable = false)
    val password: String,

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    val role: UserRole
) : BaseEntity() {
    companion object {
        @JvmStatic
        fun ofDomain(user: User): UserEntity {
            return UserEntity(
                email = user.email,
                password = user.password,
                role = user.role
            )
        }
    }

    fun toDomain(): User {
        return User(
            id = id,
            email = email,
            password = password,
            role = role,
            createdTime = createdTime,
            updatedTime = updatedTime
        )
    }
}
