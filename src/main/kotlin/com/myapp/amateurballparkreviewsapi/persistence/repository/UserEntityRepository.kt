package com.myapp.amateurballparkreviewsapi.persistence.repository

import com.myapp.amateurballparkreviewsapi.persistence.entity.UserEntity
import org.springframework.data.jpa.repository.JpaRepository

interface UserEntityRepository : JpaRepository<UserEntity, Int> {
    fun findByMailAddress(mailAddress: String): UserEntity?
}
