package by.itechart.smg.security.authentication.impl

import by.itechart.smg.repository.auth.AuthenticationDataRepository
import by.itechart.smg.security.authentication.UserDetailsFactory
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserDetailsServiceImpl(
        private val authenticationDataRepository: AuthenticationDataRepository,
        private val userDetailsFactory: UserDetailsFactory
): UserDetailsService {

    override fun loadUserByUsername(username: String?): UserDetails {
        val authData =
                if(!username.isNullOrBlank())
                    authenticationDataRepository.findAuthenticationDataByUsername(username)
                else null

        authData?.let {
            return userDetailsFactory.create(authData)
        }

        throw Exception("Meh no user with specified name");
    }
}