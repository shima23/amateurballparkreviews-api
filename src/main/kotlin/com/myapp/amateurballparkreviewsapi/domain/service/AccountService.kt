package com.myapp.amateurballparkreviewsapi.domain.service

import com.myapp.amateurballparkreviewsapi.common.util.EncryptUtils
import com.myapp.amateurballparkreviewsapi.common.util.SendMailUtils
import com.myapp.amateurballparkreviewsapi.domain.factory.AccountFactory
import com.myapp.amateurballparkreviewsapi.domain.model.Account
import com.myapp.amateurballparkreviewsapi.domain.repository.AccountRepository
import com.myapp.amateurballparkreviewsapi.persistence.entity.AccountEntity
import com.myapp.amateurballparkreviewsapi.presentation.dto.AccountRegisterRequestDto
import com.myapp.amateurballparkreviewsapi.presentation.dto.AccountResponseDto
import com.myapp.amateurballparkreviewsapi.presentation.dto.ChangePasswordRequestDto
import com.sendgrid.Method
import com.sendgrid.Request
import com.sendgrid.helpers.mail.Mail
import com.sendgrid.helpers.mail.objects.Email
import com.sendgrid.helpers.mail.objects.Personalization
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.security.SecureRandom
import kotlin.streams.asSequence

@Service
class AccountService(private val accountRepository: AccountRepository,
                     private val accountFactory: AccountFactory,
                     private val sendMailUtils: SendMailUtils) {

    @Value("\${account.register.template.id:#{null}}")
    private var registerTemplateId: String? = null

    @Value("\${account.change.password.template.id:#{null}}")
    private var changePasswordTemplateId: String? = null

    @Value("\${abl.from.address:#{null}}")
    private var fromAddress: String? = null

    @Value("\${abl.login.url:#{null}}")
    private var loginUrl: String? = null

    fun registerAccount(reqDto: AccountRegisterRequestDto): AccountResponseDto {
        // メールアドレス重複チェック
        if (accountRepository.findByMailAddress(reqDto.mailAddress) != null) {
            throw Exception()
        }
        val tempPassword = generateTempPassword()
        val account = accountFactory.createAccountForEntity(reqDto, tempPassword)
        val entity = accountRepository.registerAccount(account)

        // アカウント登録完了メール送信
        sendMailUtils.sendTemplateMail(createAccountNotifyMail(tempPassword, reqDto))
        return createAccountResponseDto(entity)
    }

    fun changePassword(reqDto: ChangePasswordRequestDto): AccountResponseDto {

        if (accountRepository.findById(reqDto.accountId).encryptPassword
            != EncryptUtils.encrypt(reqDto.oldPassword)) {
            throw Exception()
        }

        val encryptNewPassword = EncryptUtils.encrypt(reqDto.newPassword)
        val entity = accountRepository.changePassword(reqDto.accountId, encryptNewPassword)

        // パスワード変更完了メール送信
        sendMailUtils.sendTemplateMail(createChangePasswordMail(entity.mailAddress!!, entity.nickname!!))
        return createAccountResponseDto(entity)
    }

    private fun generateTempPassword(): String {
        val source = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~!@?#$%&*=+-^"
        return SecureRandom().ints(20, 0, source.length)
            .asSequence()
            .map(source::get)
            .joinToString("")
    }

    private fun createAccountResponseDto(entity: AccountEntity): AccountResponseDto {
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

    private fun createAccountNotifyMail(tempPassword: String, reqDto: AccountRegisterRequestDto): Request {
        val mail = Mail()
        mail.from = Email(fromAddress)
        mail.subject = "[AmateurBallparkReviews]アカウント登録完了のお知らせ"
        mail.templateId = registerTemplateId

        val personalization = Personalization()
        personalization.addTo(Email(reqDto.mailAddress, reqDto.nickname))
        personalization.addDynamicTemplateData("subject", mail.subject)
        personalization.addDynamicTemplateData("from", mail.from)
        personalization.addDynamicTemplateData("to", Email(reqDto.mailAddress, reqDto.nickname))
        personalization.addDynamicTemplateData("nickname", reqDto.nickname)
        personalization.addDynamicTemplateData("tempPassword", tempPassword)
        personalization.addDynamicTemplateData("loginUrl", loginUrl)
        mail.addPersonalization(personalization)

        return Request().apply {
            method = Method.POST
            endpoint = "mail/send"
            body = mail.build()
        }
    }

    private fun createChangePasswordMail(mailAddress: String, nickname: String): Request {
        val mail = Mail()
        mail.from = Email(fromAddress)
        mail.subject = "[AmateurBallparkReviews]パスワード変更完了のお知らせ"
        mail.templateId = changePasswordTemplateId

        val personalization = Personalization()
        personalization.addTo(Email(mailAddress, nickname))
        personalization.addDynamicTemplateData("subject", mail.subject)
        personalization.addDynamicTemplateData("from", mail.from)
        personalization.addDynamicTemplateData("to", Email(mailAddress, nickname))
        personalization.addDynamicTemplateData("nickname", nickname)
        mail.addPersonalization(personalization)

        return Request().apply {
            method = Method.POST
            endpoint = "mail/send"
            body = mail.build()
        }
    }
}
