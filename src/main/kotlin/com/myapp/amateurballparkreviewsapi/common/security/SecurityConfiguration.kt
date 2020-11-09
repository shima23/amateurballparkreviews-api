package com.myapp.amateurballparkreviewsapi.common.security

import com.myapp.amateurballparkreviewsapi.domain.repository.UserRepository
import com.myapp.amateurballparkreviewsapi.domain.service.CustomAuthenticationUserDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AccountStatusUserDetailsChecker
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider


@Configuration
@EnableWebSecurity
class SecurityConfiguration(private val userRepository: UserRepository) : WebSecurityConfigurerAdapter() {

    @Bean
    fun authenticationUserDetailsService(): CustomAuthenticationUserDetailsService {
        return CustomAuthenticationUserDetailsService(userRepository)
    }

    @Bean
    fun preAuthenticatedAuthenticationProvider(): PreAuthenticatedAuthenticationProvider {
        return PreAuthenticatedAuthenticationProvider().apply {
            this.setPreAuthenticatedUserDetailsService(authenticationUserDetailsService())
            this.setUserDetailsChecker(AccountStatusUserDetailsChecker())
        }
    }

    fun initPreAuthFilter(): PreAuthFilter {
        return PreAuthFilter().apply {
            this.setAuthenticationManager(authenticationManager())
        }
    }

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.authenticationProvider(preAuthenticatedAuthenticationProvider())
    }

    override fun configure(web: WebSecurity) {
        // login時はfilterの処理を行わない
        web.ignoring().antMatchers("/login", "/logout")
    }

    override fun configure(http: HttpSecurity) {
        http.csrf().disable()
            .authorizeRequests()
            .mvcMatchers("/login").permitAll()
            .anyRequest().authenticated()
            .and()
            .logout().logoutUrl("/logout").permitAll()
            .and()
            // 一度認可されるとFilterの処理はSKIPされる
            .addFilterBefore(initPreAuthFilter(), PreAuthFilter::class.java)
            .cors()
    }
}


