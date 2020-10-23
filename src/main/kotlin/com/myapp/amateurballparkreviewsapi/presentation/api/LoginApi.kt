package com.myapp.amateurballparkreviewsapi.presentation.api

import com.myapp.amateurballparkreviewsapi.domain.service.LoginService
import com.myapp.amateurballparkreviewsapi.presentation.dto.LoginRequestDto
import com.myapp.amateurballparkreviewsapi.presentation.dto.LoginResponseDto
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LoginApi(private val loginService: LoginService) {

    @CrossOrigin
    @PostMapping("/login")
    fun login(@RequestBody reqDto: LoginRequestDto): LoginResponseDto {
        return loginService.login(reqDto)
    }
}
