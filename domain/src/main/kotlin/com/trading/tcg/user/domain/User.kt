package com.trading.tcg.user.domain

import com.trading.tcg.global.domain.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "user")
class User(
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
): BaseEntity() {
    companion object {
        const val emailFormat = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}\$"
        const val emailMinLength = 5
        const val emailMaxLength = 50
        const val passwordFormat = "^(?=.*[a-zA-Z])(?=.*[!@#\$%^*+=-])(?=.*[0-9]).{8,15}\$"
        const val passwordMinLength = 8
        const val passwordMaxLength = 20
    }
}
