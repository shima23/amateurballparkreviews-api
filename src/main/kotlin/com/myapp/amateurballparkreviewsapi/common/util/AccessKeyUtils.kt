package com.myapp.amateurballparkreviewsapi.common.util

import org.joda.time.DateTime
import java.security.MessageDigest

object AccessKeyUtils {

    private const val AUTH_FIX_KEY = "abr"

    fun create(accountId: Int): String {
        val now = DateTime.now()
        val strAccessKey = accountId.toString() + now + AUTH_FIX_KEY
        return  MessageDigest.getInstance("SHA-512")
            .digest(strAccessKey.toByteArray())
            .joinToString("") {
                "%02x".format(it)
            }
    }

}
