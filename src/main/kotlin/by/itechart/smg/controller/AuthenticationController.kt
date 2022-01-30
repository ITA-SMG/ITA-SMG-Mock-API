package by.itechart.smg.controller

import by.itechart.smg.dto.authentication.AuthenticationInputDTO
import by.itechart.smg.dto.authentication.RefreshTokenInputDTO
import by.itechart.smg.service.AuthenticationService
import by.itechart.smg.service.EmployeeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/auth")
class AuthenticationController @Autowired constructor(
        private val authenticationService: AuthenticationService,
        private val employeeService: EmployeeService
) {

    @PostMapping("/authenticate")
    fun authenticate(@RequestBody data: AuthenticationInputDTO) =
            authenticationService.authenticate(data.username, data.password)

    @PostMapping("/token/refresh")
    fun refreshTokens(@RequestBody data: RefreshTokenInputDTO) =
            authenticationService.refreshTokens(data.refreshToken)
}