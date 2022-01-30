package by.itechart.smg.service

import by.itechart.smg.dto.authentication.AuthenticationOutputDTO
import by.itechart.smg.model.auth.AuthenticationData

interface AuthenticationService {
    fun findAuthenticationDataByUsername(username: String): AuthenticationData?
    fun authenticate(username: String, password: String): AuthenticationOutputDTO
    fun refreshTokens(refreshToken: String): AuthenticationOutputDTO
}