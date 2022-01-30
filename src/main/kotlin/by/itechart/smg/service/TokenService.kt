package by.itechart.smg.service

import by.itechart.smg.model.auth.token.AuthorizationToken
import by.itechart.smg.model.auth.token.RefreshToken

interface TokenService {
    fun createAuthorizationToken(username: String): AuthorizationToken
    fun createRefreshToken(username: String): RefreshToken
    fun isAuthTokenExists(token: String): AuthorizationToken?
    fun isRefreshTokenExists(token: String): RefreshToken?
}