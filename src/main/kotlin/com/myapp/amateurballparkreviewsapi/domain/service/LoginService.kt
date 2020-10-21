package com.myapp.amateurballparkreviewsapi.domain.service

import com.myapp.amateurballparkreviewsapi.common.util.EncryptUtils
import com.myapp.amateurballparkreviewsapi.domain.repository.AccountRepository
import com.myapp.amateurballparkreviewsapi.presentation.dto.LoginRequestDto
import com.myapp.amateurballparkreviewsapi.presentation.dto.LoginResponseDto
import org.springframework.stereotype.Service

@Service
class LoginService(private val repository: AccountRepository) {

    fun login(reqDto: LoginRequestDto): LoginResponseDto {
        val entity = repository.findByMailAddress(reqDto.mailAddress) ?: throw Exception()
        // ログイン認証
        return if (entity.encryptPassword == EncryptUtils.encrypt(reqDto.password)) {
            // アクセスキー更新
            val accessKey = repository.updateAccessKey(reqDto.mailAddress)
            LoginResponseDto(true, reqDto.mailAddress, accessKey)
        } else {
            LoginResponseDto(false, reqDto.mailAddress, "")
        }
    }
}
