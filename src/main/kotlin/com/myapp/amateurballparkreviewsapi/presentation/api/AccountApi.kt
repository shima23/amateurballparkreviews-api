package com.myapp.amateurballparkreviewsapi.presentation.api

import com.myapp.amateurballparkreviewsapi.domain.service.AccountService
import com.myapp.amateurballparkreviewsapi.presentation.dto.AccountRegisterRequestDto
import com.myapp.amateurballparkreviewsapi.presentation.dto.AccountResponseDto
import org.springframework.web.bind.annotation.*

@RestController
class AccountApi(private val accountService: AccountService) {

    @CrossOrigin
    @PostMapping("/account", consumes = ["application/json;charset=UTF-8"], produces = ["application/json;charset=UTF-8"])
    fun registerAccount(@RequestBody req : AccountRegisterRequestDto): AccountResponseDto {
        return accountService.registerAccount(req)
    }
}