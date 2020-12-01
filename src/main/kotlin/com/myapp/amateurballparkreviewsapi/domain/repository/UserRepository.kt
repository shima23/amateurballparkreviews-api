package com.myapp.amateurballparkreviewsapi.domain.repository

import com.myapp.amateurballparkreviewsapi.domain.model.User
import com.myapp.amateurballparkreviewsapi.persistence.entity.UserEntity
import com.myapp.amateurballparkreviewsapi.persistence.repository.UserEntityRepository
import org.springframework.stereotype.Repository

@Repository
class UserRepository(private val entityRepository: UserEntityRepository) {

    fun findById(id: Int): UserEntity {
        return entityRepository.findById(id).get()
    }

    fun findByMailAddress(mailAddress: String): UserEntity? {
        return entityRepository.findByMailAddress(mailAddress)
    }

    fun registerUser(user: User): UserEntity {
        val entity = UserEntity(user)
        return entityRepository.save(entity)
    }

    fun changePassword(mailAddress: String, newPassword: String): UserEntity {
        val entity = entityRepository.findByMailAddress(mailAddress)!!
        entity.apply {
            encryptPassword = newPassword
        }
        entityRepository.save(entity)
        return entity
    }
}
