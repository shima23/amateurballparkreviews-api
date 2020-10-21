package com.myapp.amateurballparkreviewsapi.common.util

import java.security.MessageDigest

object EncryptUtils {

    fun encrypt(password: String): String {
        return MessageDigest.getInstance("SHA-512")
            .digest(password.toByteArray())
            .joinToString("") {
                "%02x".format(it)
            }
    }
}
