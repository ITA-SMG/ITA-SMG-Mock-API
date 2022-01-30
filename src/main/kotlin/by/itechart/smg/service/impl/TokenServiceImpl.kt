package by.itechart.smg.service.impl

import by.itechart.smg.model.auth.token.AuthorizationToken
import by.itechart.smg.model.auth.token.RefreshToken
import by.itechart.smg.repository.auth.AuthorizationTokenRepository
import by.itechart.smg.repository.auth.RefreshTokenRepository
import by.itechart.smg.security.jwt.JWTTokenProvider
import by.itechart.smg.service.TokenService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class TokenServiceImpl(
        private val authorizationTokenRepository: AuthorizationTokenRepository,
        private val refreshTokenRepository: RefreshTokenRepository,
        private val jwtTokenProvider: JWTTokenProvider,
        @Value("\${jwt.token.expire-multiplier}")
        private val expireMultiplier: Long
): TokenService {

    @Transactional
    override fun createAuthorizationToken(username: String): AuthorizationToken {
        val token = jwtTokenProvider.createToken(username)
        val authToken = AuthorizationToken(token)

        authorizationTokenRepository.save(authToken)

        return authToken
    }

    @Transactional
    override fun createRefreshToken(username: String): RefreshToken {
        val token = jwtTokenProvider.createToken(username, expireMultiplier)
        val refreshToken = RefreshToken(token)

        refreshTokenRepository.save(refreshToken)

        return refreshToken
    }

    @Transactional
    override fun isAuthTokenExists(token: String) =
            authorizationTokenRepository.findAuthorizationTokenByToken(token)

    @Transactional
    override fun isRefreshTokenExists(token: String) =
            refreshTokenRepository.findRefreshTokenByToken(token)
}