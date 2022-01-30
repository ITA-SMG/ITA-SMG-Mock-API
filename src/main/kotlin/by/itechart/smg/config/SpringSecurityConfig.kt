package by.itechart.smg.config

import by.itechart.smg.security.jwt.JWTConfigurer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class SpringSecurityConfig(
        private val jwtConfigurer: JWTConfigurer
): WebSecurityConfigurerAdapter() {

    private val authenticationEndpoint: String = "/api/v1/auth/**"

    @Bean
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    override fun configure(http: HttpSecurity?) {
        http?.let {
            http.cors().and()
                    .httpBasic()
                    .disable()
                    .csrf()
                    .disable()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .authorizeRequests()
                    .antMatchers(authenticationEndpoint)
                    .permitAll()
                    .anyRequest()
                    .authenticated()
                    .and()
                    .apply(jwtConfigurer)
        }
    }
}