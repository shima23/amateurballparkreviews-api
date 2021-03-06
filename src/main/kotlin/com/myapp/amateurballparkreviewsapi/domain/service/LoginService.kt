package com.myapp.amateurballparkreviewsapi.domain.service

import com.myapp.amateurballparkreviewsapi.common.util.EncryptUtils
import com.myapp.amateurballparkreviewsapi.common.util.JWTUtils
import com.myapp.amateurballparkreviewsapi.domain.repository.UserRepository
import com.myapp.amateurballparkreviewsapi.presentation.dto.LoginRequestDto
import com.myapp.amateurballparkreviewsapi.presentation.dto.LoginResponseDto
import org.springframework.stereotype.Service

@Service
class LoginService(private val repository: UserRepository) {

    fun login(reqDto: LoginRequestDto): LoginResponseDto {
        val entity = repository.findByMailAddress(reqDto.mailAddress) ?: throw Exception()
        // ログイン認証
        return if (entity.encryptPassword == EncryptUtils.encrypt(reqDto.password)) {
            // トークンを更新
            val token = JWTUtils.createToken(reqDto.mailAddress)
            LoginResponseDto(token)
        } else {
            throw Exception()
        }
    }
}
