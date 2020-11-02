package com.myapp.amateurballparkreviewsapi.domain.service

import com.myapp.amateurballparkreviewsapi.common.util.EncryptUtils
import com.myapp.amateurballparkreviewsapi.common.util.SendMailUtils
import com.myapp.amateurballparkreviewsapi.domain.factory.UserFactory
import com.myapp.amateurballparkreviewsapi.domain.model.User
import com.myapp.amateurballparkreviewsapi.domain.repository.UserRepository
import com.myapp.amateurballparkreviewsapi.persistence.entity.UserEntity
import com.myapp.amateurballparkreviewsapi.presentation.dto.UserRegisterRequestDto
import com.myapp.amateurballparkreviewsapi.presentation.dto.UserResponseDto
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
class UserService(private val userRepository: UserRepository,
                  private val userFactory: UserFactory,
                  private val sendMailUtils: SendMailUtils) {

    @Value("\${user.register.template.id:#{null}}")
    private var registerTemplateId: String? = null

    @Value("\${user.change.password.template.id:#{null}}")
    private var changePasswordTemplateId: String? = null

    @Value("\${abl.from.address:#{null}}")
    private var fromAddress: String? = null

    @Value("\${abl.login.url:#{null}}")
    private var loginUrl: String? = null

    fun registerUser(reqDto: UserRegisterRequestDto): UserResponseDto {
        // メールアドレス重複チェック
        if (userRepository.findByMailAddress(reqDto.mailAddress) != null) {
            throw Exception()
        }
        val tempPassword = generateTempPassword()
        val user = userFactory.createUserForEntity(reqDto, tempPassword)
        val entity = userRepository.registerUser(user)

        // ユーザー登録完了メール送信
        sendMailUtils.sendTemplateMail(createUserNotifyMail(tempPassword, reqDto))
        return createUserResponseDto(entity)
    }

    fun changePassword(reqDto: ChangePasswordRequestDto): UserResponseDto {

        if (userRepository.findById(reqDto.userId).encryptPassword
            != EncryptUtils.encrypt(reqDto.oldPassword)) {
            throw Exception()
        }

        val encryptNewPassword = EncryptUtils.encrypt(reqDto.newPassword)
        val entity = userRepository.changePassword(reqDto.userId, encryptNewPassword)

        // パスワード変更完了メール送信
        sendMailUtils.sendTemplateMail(createChangePasswordMail(entity.mailAddress!!, entity.nickname!!))
        return createUserResponseDto(entity)
    }

    private fun generateTempPassword(): String {
        val source = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~!@?#$%&*=+-^"
        return SecureRandom().ints(20, 0, source.length)
            .asSequence()
            .map(source::get)
            .joinToString("")
    }

    private fun createUserResponseDto(entity: UserEntity): UserResponseDto {
        return UserResponseDto(User(
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

    private fun createUserNotifyMail(tempPassword: String, reqDto: UserRegisterRequestDto): Request {
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
