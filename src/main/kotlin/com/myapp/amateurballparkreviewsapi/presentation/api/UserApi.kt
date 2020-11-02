package com.myapp.amateurballparkreviewsapi.presentation.api

import com.myapp.amateurballparkreviewsapi.domain.service.UserService
import com.myapp.amateurballparkreviewsapi.presentation.dto.UserRegisterRequestDto
import com.myapp.amateurballparkreviewsapi.presentation.dto.UserResponseDto
import com.myapp.amateurballparkreviewsapi.presentation.dto.ChangePasswordRequestDto
import org.springframework.web.bind.annotation.*

@RestController
class UserApi(private val userService: UserService) {

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

    @CrossOrigin
    @GetMapping("/auth/user")
    fun getUser() {
        print("aaaa")
    }
}
