package com.myapp.amateurballparkreviewsapi.domain.model

import org.joda.time.DateTime
import java.sql.Timestamp

data class Account(
    var id: Int?,
    var mailAddress: String,
    var nickname: String,
    var encrypt_password: String,
    var accessKey: String?,
    var profileImg: String?,
    var profileText: String?,
    var createdAt : Timestamp,
    var updatedAt : Timestamp
)
