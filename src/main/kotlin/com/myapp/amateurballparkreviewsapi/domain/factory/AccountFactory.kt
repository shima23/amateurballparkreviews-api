package com.myapp.amateurballparkreviewsapi.domain.factory

import com.myapp.amateurballparkreviewsapi.domain.model.Account
import com.myapp.amateurballparkreviewsapi.presentation.dto.AccountRegisterRequestDto
import org.springframework.stereotype.Component
import java.security.MessageDigest
import java.util.*

@Component
class AccountFactory  {
    fun createAccountForEntity(reqDto : AccountRegisterRequestDto, tempPassword: String) : Account {
        return Account(
            id = null,
            mailAddress = reqDto.mailAddress,
            nickname = reqDto.nickname,
            encrypt_password = encrypt(tempPassword),
            profileImg = null,
            createdAt = Date()
        )
    }

    private fun encrypt(password: String): String {
        return MessageDigest.getInstance("SHA-512")
            .digest(password.toByteArray())
            .joinToString("") {
                "%02x".format(it)
            }
    }
}
