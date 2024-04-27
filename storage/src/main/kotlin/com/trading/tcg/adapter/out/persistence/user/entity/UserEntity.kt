package com.trading.tcg.adapter.out.persistence.user.entity

import com.trading.tcg.adapter.out.persistence.global.BaseEntity
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

    @Column(name = "kakao_id")
    val kakaoId: String?,

    @Column(name = "apple_id")
    val appleId: String?,

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    val role: UserRole,

    @Column(name = "fcm_token")
    val fcmToken: String?
) : BaseEntity() {
    companion object {
        @JvmStatic
        fun ofDomain(user: User): UserEntity {
            return UserEntity(
                email = user.email,
                password = user.password,
                kakaoId = user.kakaoId,
                appleId = user.appleId,
                role = user.role,
                fcmToken = user.fcmToken,
            )
        }
    }

    fun toDomain(): User {
        return User(
            id = id,
            email = email,
            password = password,
            kakaoId = kakaoId,
            appleId = appleId,
            role = role,
            fcmToken = fcmToken,
            createdTime = createdTime,
            updatedTime = updatedTime
        )
    }
}
