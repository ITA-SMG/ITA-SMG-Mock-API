package by.itechart.smg.security.jwt.impl

import by.itechart.smg.security.jwt.JWTConfigurer
import by.itechart.smg.security.jwt.JWTTokenFilter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.stereotype.Component

@Component
class JWTConfigurerImpl(
        private val jwtTokenFilter: JWTTokenFilter
): JWTConfigurer() {
    override fun configure(builder: HttpSecurity?) {
        builder?.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter::class.java)
    }
}