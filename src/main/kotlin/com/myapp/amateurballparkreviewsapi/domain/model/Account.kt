package com.myapp.amateurballparkreviewsapi.domain.model

import java.util.*

data class Account(
    var id: Int?,
    var mailAddress: String,
    var nickname: String,
    var encrypt_password: String,
    var profileImg: String?,
    var createdAt : Date
)
