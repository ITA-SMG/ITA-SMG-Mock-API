package by.itechart.smg.security.jwt.impl

import by.itechart.smg.security.jwt.JWTTokenFilter
import by.itechart.smg.security.jwt.JWTTokenProvider
import by.itechart.smg.service.TokenService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JWTTokenFilterImpl(
        private val jwtTokenProvider: JWTTokenProvider,
        private val tokenService: TokenService
): JWTTokenFilter() {
    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        val token = jwtTokenProvider.resolveToken(request as HttpServletRequest)

        try {
            if(!token.isNullOrBlank() && jwtTokenProvider.validate(token)
                    && tokenService.isAuthTokenExists(token) !== null) {

                val auth = jwtTokenProvider.getAuthentication(token)

                auth?.let {
                    val details = auth.principal as UserDetails

                    if(details.isEnabled) {
                        SecurityContextHolder.getContext().authentication = auth
                    }
                }
            }

            chain?.doFilter(request, response)
        } catch (ex: Exception) {
            ex.printStackTrace()
            (response as HttpServletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED)
        }
    }
}