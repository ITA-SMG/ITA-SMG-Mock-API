package by.itechart.smg.security.jwt

import org.springframework.security.config.annotation.SecurityConfigurerAdapter
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.DefaultSecurityFilterChain

abstract class JWTConfigurer: SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity>()