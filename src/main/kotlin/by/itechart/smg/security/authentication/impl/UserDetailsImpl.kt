package by.itechart.smg.security.authentication.impl

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

data class UserDetailsImpl(
        private val username: String,
        private val password: String
): UserDetails {
    override fun getAuthorities() = mutableListOf<GrantedAuthority>()

    override fun getPassword() = password

    override fun getUsername(): String = username

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true
}