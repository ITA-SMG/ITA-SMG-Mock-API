package by.itechart.smg.repository.auth

import by.itechart.smg.model.auth.AuthenticationData
import org.springframework.data.repository.CrudRepository

interface AuthenticationDataRepository: CrudRepository<AuthenticationData, Long> {
    fun findAuthenticationDataByUsername(username: String): AuthenticationData?
}