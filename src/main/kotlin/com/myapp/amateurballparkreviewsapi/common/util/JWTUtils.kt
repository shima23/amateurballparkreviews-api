package com.myapp.amateurballparkreviewsapi.common.util

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTCreationException

object JWTUtils {

    fun createToken(mailAddress: String): String {
        lateinit var token: String
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
