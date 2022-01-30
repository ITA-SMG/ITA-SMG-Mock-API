package by.itechart.smg.security.jwt

import org.springframework.security.core.Authentication
import javax.servlet.http.HttpServletRequest

interface JWTTokenProvider {
    fun createToken(username: String, expireMultiplier: Long = 1): String
    fun validate(token: String): Boolean
    fun getUsername(token: String): String
    fun getAuthentication(token: String): Authentication?
    fun resolveToken(request: HttpServletRequest): String?
}