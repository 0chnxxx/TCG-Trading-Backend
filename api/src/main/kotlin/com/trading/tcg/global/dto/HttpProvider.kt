package com.trading.tcg.global.dto

import com.trading.tcg.application.user.domain.User
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class HttpProvider(
    private val user: User? = null
) : Provider, UserDetails {
    override fun getUser(): User? {
        return user
    }

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf<GrantedAuthority>(SimpleGrantedAuthority("ROLE_" + user?.role?.name))
    }

    override fun getPassword(): String? {
        return user?.password
    }

    override fun getUsername(): String? {
        return user?.email
    }

    override fun isAccountNonExpired(): Boolean {
        return false
    }

    override fun isAccountNonLocked(): Boolean {
        return false
    }

    override fun isCredentialsNonExpired(): Boolean {
        return false
    }

    override fun isEnabled(): Boolean {
        return false
    }
}
