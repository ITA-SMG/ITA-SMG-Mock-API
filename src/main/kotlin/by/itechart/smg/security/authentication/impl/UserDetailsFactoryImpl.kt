package by.itechart.smg.security.authentication.impl

import by.itechart.smg.model.auth.AuthenticationData
import by.itechart.smg.security.authentication.UserDetailsFactory
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component

@Component
class UserDetailsFactoryImpl: UserDetailsFactory {
    override fun create(data: AuthenticationData): UserDetails {
        return UserDetailsImpl(data.username, data.passwordHash)
    }
}