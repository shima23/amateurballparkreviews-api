package com.myapp.amateurballparkreviewsapi.presentation.api

import com.myapp.amateurballparkreviewsapi.domain.service.UserService
import com.myapp.amateurballparkreviewsapi.presentation.dto.ChangePasswordRequestDto
import com.myapp.amateurballparkreviewsapi.presentation.dto.UserRegisterRequestDto
import com.myapp.amateurballparkreviewsapi.presentation.dto.UserResponseDto
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
class UserApi(private val userService: UserService) {

    @GetMapping("/auth/user")
    fun getUser(request: HttpServletRequest): UserResponseDto {
        val credential = SecurityContextHolder.getContext().authentication.credentials.toString()
        SecurityContextHolder.clearContext()
        return userService.getUser(credential)
    }

    @CrossOrigin
    @PostMapping("/user", consumes = ["application/json;charset=UTF-8"], produces = ["application/json;charset=UTF-8"])
    fun registerUser(@RequestBody req : UserRegisterRequestDto): UserResponseDto {
        return userService.registerUser(req)
    }

    @CrossOrigin
    @PostMapping("/password/change", consumes = ["application/json;charset=UTF-8"], produces = ["application/json;charset=UTF-8"])
    fun changePassword(@RequestBody req : ChangePasswordRequestDto): UserResponseDto {
        return userService.changePassword(req)
    }
}
