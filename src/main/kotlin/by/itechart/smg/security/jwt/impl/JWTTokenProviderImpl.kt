package by.itechart.smg.security.jwt.impl

import io.jsonwebtoken.*
import by.itechart.smg.security.jwt.JWTTokenProvider
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component
import java.util.*
import javax.annotation.PostConstruct
import javax.servlet.http.HttpServletRequest

@Component
class JWTTokenProviderImpl(
        private val userDetailsService: UserDetailsService,

        @Value("\${jwt.token.secret}")
        private var secret: String,

        @Value("\${jwt.token.expires}")
        private val expires: Long //Milliseconds
): JWTTokenProvider {

    @PostConstruct
    private fun init() {
        secret = Base64.getEncoder().encodeToString(secret.encodeToByteArray())
    }

    override fun createToken(username: String, expireMultiplier: Long): String {
        val claims = Jwts.claims().setSubject(username)

        val now = Date()
        val validity = Date(now.time + expires * expireMultiplier)

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(SignatureAlgorithm.HS256, secret)
                .compact()
    }

    override fun validate(token: String): Boolean {
        try {
            val claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token)

            return !claims.body.expiration.before(Date())
        } catch (ex: Exception) {
            throw Exception("Invalid JWT")
        }
    }

    override fun getUsername(token: String): String =
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token).body.subject

    override fun getAuthentication(token: String): Authentication? {
        val details = userDetailsService.loadUserByUsername(getUsername(token))

        return UsernamePasswordAuthenticationToken(details, null, details.authorities)
    }

    override fun resolveToken(request: HttpServletRequest): String? {
        val bearer = request.getHeader("Authorization")

        bearer?.let {
            if(it.startsWith("Bearer ")) return bearer.substring(7, bearer.length)
        }

        return null
    }
}