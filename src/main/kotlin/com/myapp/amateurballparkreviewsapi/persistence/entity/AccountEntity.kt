package com.myapp.amateurballparkreviewsapi.persistence.entity

import com.myapp.amateurballparkreviewsapi.domain.model.Account
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "EX_ACCOUNT")
data class AccountEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    var id: Int? = null,

    @Column(name = "MAIL_ADDRESS", nullable = false, length = 100)
    var mailAddress: String? = null,

    @Column(name = "NICKNAME", nullable = true, length = 100)
    var nickname: String? = null,

    @Column(name = "ENCRYPT_PASSWORD", nullable = true, length = 255)
    var encryptPassword: String? = null,

    @Column(name = "PROFILE_IMG", nullable = true, length = 100)
    var profileImg: String? = null,

    @Column(name = "CREATED_AT", nullable = true)
    var createdAt: Date? = null
) {
    constructor(account: Account) : this(
        mailAddress = account.mailAddress,
        nickname = account.nickname,
        encryptPassword = account.encrypt_password,
        profileImg = account.profileImg,
        createdAt = account.createdAt
    )
}
