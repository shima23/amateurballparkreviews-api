package com.myapp.amateurballparkreviewsapi.domain.service

import com.myapp.amateurballparkreviewsapi.domain.factory.AccountFactory
import com.myapp.amateurballparkreviewsapi.domain.model.Account
import com.myapp.amateurballparkreviewsapi.domain.repository.AccountRepository
import com.myapp.amateurballparkreviewsapi.presentation.dto.AccountRegisterRequestDto
import com.myapp.amateurballparkreviewsapi.presentation.dto.AccountResponseDto
import org.springframework.stereotype.Service
import java.security.SecureRandom
import kotlin.streams.asSequence

@Service
class AccountService(private val accountRepository: AccountRepository,
                     private val accountFactory: AccountFactory) {

    fun registerAccount(reqDto: AccountRegisterRequestDto): AccountResponseDto {
        // メールアドレス重複チェック
        if (accountRepository.findByMailAddress(reqDto.mailAddress) != null) {
            throw Exception()
        }
        val tempPassword = generateTempPassword()
        val account = accountFactory.createAccountForEntity(reqDto, tempPassword)
        val entity = accountRepository.registerAccount(account)

        //TODO:SendGridメール送信

        return AccountResponseDto(Account(
            entity.id,
            entity.mailAddress!!,
            entity.nickname!!,
            entity.encryptPassword!!,
            entity.accessKey,
            entity.profileImg,
            entity.profileText,
            entity.createdAt!!,
            entity.updatedAt!!
        ))
    }

    private fun generateTempPassword(): String {
        val source = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~!@?#$%&*=+-^"
        return SecureRandom().ints(20, 0, source.length)
            .asSequence()
            .map(source::get)
            .joinToString("")
    }
}
