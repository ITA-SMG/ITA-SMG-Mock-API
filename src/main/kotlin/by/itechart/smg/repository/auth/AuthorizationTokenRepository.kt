package by.itechart.smg.repository.auth

import by.itechart.smg.model.auth.token.AuthorizationToken
import org.springframework.data.repository.CrudRepository

interface AuthorizationTokenRepository: CrudRepository<AuthorizationToken, Long> {
    fun findAuthorizationTokenByToken(token: String): AuthorizationToken?
}