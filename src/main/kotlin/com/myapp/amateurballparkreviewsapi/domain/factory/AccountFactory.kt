package com.myapp.amateurballparkreviewsapi.domain.factory

import com.myapp.amateurballparkreviewsapi.common.util.AccessKeyUtils
import com.myapp.amateurballparkreviewsapi.common.util.EncryptUtils
import com.myapp.amateurballparkreviewsapi.domain.model.Account
import com.myapp.amateurballparkreviewsapi.persistence.entity.AccountEntity
import com.myapp.amateurballparkreviewsapi.presentation.dto.AccountRegisterRequestDto
import org.joda.time.DateTime
import org.springframework.stereotype.Component
import java.sql.Timestamp
import java.util.*

@Component
class AccountFactory  {
    fun createAccountForEntity(reqDto : AccountRegisterRequestDto, tempPassword: String) : Account {
        return Account(
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
