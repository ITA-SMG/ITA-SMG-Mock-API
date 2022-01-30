package by.itechart.smg.repository.auth

import by.itechart.smg.model.auth.token.RefreshToken
import org.springframework.data.repository.CrudRepository

interface RefreshTokenRepository: CrudRepository<RefreshToken, Long> {
    fun findRefreshTokenByToken(token: String): RefreshToken?
}