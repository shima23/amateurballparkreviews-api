package com.myapp.amateurballparkreviewsapi.domain.repository

import com.myapp.amateurballparkreviewsapi.common.util.AccessKeyUtils
import com.myapp.amateurballparkreviewsapi.domain.model.Account
import com.myapp.amateurballparkreviewsapi.persistence.entity.AccountEntity
import com.myapp.amateurballparkreviewsapi.persistence.repository.AccountEntityRepository
import javassist.NotFoundException
import org.joda.time.DateTime
import org.springframework.stereotype.Repository
import java.sql.Timestamp

@Repository
class AccountRepository(private val entityRepository: AccountEntityRepository) {

    fun findById(id: Int): AccountEntity {
        return entityRepository.findById(id).get()
    }

    fun findByMailAddress(mailAddress: String): AccountEntity? {
        return entityRepository.findByMailAddress(mailAddress)
    }

    fun updateAccessKey(mailAddress: String): String {
        val entity = entityRepository.findByMailAddress(mailAddress) ?: throw Exception()
        val newAccessKey = AccessKeyUtils.create(entity.id!!)
        entity.apply {
            accessKey = newAccessKey
        }
        entityRepository.save(entity)
        return newAccessKey
    }

    fun registerAccount(account: Account): AccountEntity {
        val entity = AccountEntity(account)
        return entityRepository.save(entity)
    }

    fun changePassword(accountId: Int, newPassword: String): AccountEntity {
        val entity = entityRepository.findById(accountId).get()
        entity.apply {
            encryptPassword = newPassword
        }
        entityRepository.save(entity)
        return entity
    }
}
