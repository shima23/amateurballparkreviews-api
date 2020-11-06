package com.myapp.amateurballparkreviewsapi.common.security

import org.springframework.security.core.GrantedAuthority

class UserAuthority : GrantedAuthority {
    override fun getAuthority(): String {
        return "ROLE_USER"
    }
}
