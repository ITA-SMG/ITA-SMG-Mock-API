package by.itechart.smg.service.impl

import by.itechart.smg.dto.authentication.AuthenticationOutputDTO
import by.itechart.smg.repository.auth.AuthenticationDataRepository
import by.itechart.smg.security.jwt.JWTTokenProvider
import by.itechart.smg.service.AuthenticationService
import by.itechart.smg.service.TokenService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthenticationServiceImpl(
        private val authenticationManager: AuthenticationManager,
        private val tokenService: TokenService,
        private val authenticationDataRepository: AuthenticationDataRepository,
        private val jwtTokenProvider: JWTTokenProvider
): AuthenticationService {

    @Transactional
    override fun findAuthenticationDataByUsername(username: String) =
            authenticationDataRepository.findAuthenticationDataByUsername(username)

    override fun authenticate(username: String, password: String): AuthenticationOutputDTO {
        val authentication = authenticationManager.authenticate(UsernamePasswordAuthenticationToken(username, password))
        val userDetails = authentication.principal as UserDetails

        return AuthenticationOutputDTO(
                authorizationToken = tokenService.createAuthorizationToken(userDetails.username).token,
                refreshToken = tokenService.createRefreshToken(userDetails.username).token
        )
    }

    override fun refreshTokens(refreshToken: String): AuthenticationOutputDTO {
        val token = tokenService.isRefreshTokenExists(refreshToken)

        token?.let {
            val username = jwtTokenProvider.getUsername(token.token)
            val isValid = jwtTokenProvider.validate(token.token)

            if(!isValid) throw Exception("Refresh token was expired")

            return AuthenticationOutputDTO(
                    authorizationToken = tokenService.createAuthorizationToken(username).token,
                    refreshToken = tokenService.createRefreshToken(username).token
            )
        }

        throw Exception("Invalid refresh token")
    }
}