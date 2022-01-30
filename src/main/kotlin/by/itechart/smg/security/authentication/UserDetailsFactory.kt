package by.itechart.smg.security.authentication

import by.itechart.smg.model.auth.AuthenticationData
import org.springframework.security.core.userdetails.UserDetails

interface UserDetailsFactory {
    fun create(data: AuthenticationData): UserDetails
}