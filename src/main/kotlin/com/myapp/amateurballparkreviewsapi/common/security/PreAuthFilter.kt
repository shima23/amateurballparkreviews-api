package com.myapp.amateurballparkreviewsapi.common.security

import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter
import javax.servlet.http.HttpServletRequest

class PreAuthFilter : AbstractPreAuthenticatedProcessingFilter() {

    override fun getPreAuthenticatedCredentials(request: HttpServletRequest?): Any {
        if (request == null) throw RuntimeException("TODO")

        val authorizationHeader = request.getHeader("Authorization") ?: return ""
        val authHeaderToken = authorizationHeader.split(" ")
        // Bearer以降を返却
        return authHeaderToken[1]
    }

    override fun getPreAuthenticatedPrincipal(request: HttpServletRequest?): Any {
        return ""
    }
}
