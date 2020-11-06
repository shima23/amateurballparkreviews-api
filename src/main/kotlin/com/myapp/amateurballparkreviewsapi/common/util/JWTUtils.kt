package com.myapp.amateurballparkreviewsapi.common.util

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTCreationException
import org.joda.time.DateTime
import java.security.MessageDigest

object JWTUtils {

    private const val AUTH_FIX_KEY = "abr"

    fun create(userId: Int): String {
        val now = DateTime.now()
        val strAccessKey = userId.toString() + now + AUTH_FIX_KEY
        return  MessageDigest.getInstance("SHA-512")
            .digest(strAccessKey.toByteArray())
            .joinToString("") {
                "%02x".format(it)
            }
    }

    fun createToken(mailAddress: String): String {
        lateinit var token : String
        try {
            val algorithm = Algorithm.HMAC256("secret")
            token = JWT.create()
                .withIssuer("auth0")
                .withClaim("mailAddress", mailAddress)
                .sign(algorithm)
        } catch (e: JWTCreationException) {
            // TODO
        }
        return token
    }

    fun decodeTokenToMailAddress(token: String): String {
        val jwt = JWT.decode(token)
        return jwt.claims.getValue("mailAddress").asString()
    }

}
