package com.myapp.amateurballparkreviewsapi.common.util

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.User
import org.springframework.stereotype.Service

@Service
class AuthUtils {
    fun getMailAddress(): String {
        val user = SecurityContextHolder.getContext().authentication.principal as User
        return user.username
    }
}
