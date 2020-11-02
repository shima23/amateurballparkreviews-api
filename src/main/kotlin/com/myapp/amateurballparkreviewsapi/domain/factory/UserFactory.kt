package com.myapp.amateurballparkreviewsapi.domain.factory

import com.myapp.amateurballparkreviewsapi.common.util.EncryptUtils
import com.myapp.amateurballparkreviewsapi.domain.model.User
import com.myapp.amateurballparkreviewsapi.presentation.dto.UserRegisterRequestDto
import org.springframework.stereotype.Component
import java.sql.Timestamp

@Component
class UserFactory  {
    fun createUserForEntity(reqDto : UserRegisterRequestDto, tempPassword: String) : User {
        return User(
            id = null,
            mailAddress = reqDto.mailAddress,
            nickname = reqDto.nickname,
            encrypt_password = EncryptUtils.encrypt(tempPassword),
            accessKey = null,
            profileImg = null,
            profileText = null,
            createdAt = Timestamp(System.currentTimeMillis()),
            updatedAt = Timestamp(System.currentTimeMillis())
        )
    }

}
