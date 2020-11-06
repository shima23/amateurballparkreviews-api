package com.myapp.amateurballparkreviewsapi.domain.service

import com.myapp.amateurballparkreviewsapi.common.security.UserAuthority
import com.myapp.amateurballparkreviewsapi.common.util.JWTUtils
import com.myapp.amateurballparkreviewsapi.domain.repository.UserRepository
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken
import org.springframework.stereotype.Service

@Service
class CustomAuthenticationUserDetailsService(
    private val userRepository: UserRepository
) : AuthenticationUserDetailsService<PreAuthenticatedAuthenticationToken> {

    override fun loadUserDetails(token: PreAuthenticatedAuthenticationToken): UserDetails {
        val credentials = token.credentials.toString()
        val mailAddress = JWTUtils.decodeTokenToMailAddress(credentials)

        userRepository.findByMailAddress(mailAddress) ?: throw Exception()

        val authorities = HashSet<GrantedAuthority>()
        authorities.add(UserAuthority())

        return User(mailAddress, "", authorities)
    }
}
