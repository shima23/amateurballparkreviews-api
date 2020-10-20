package com.myapp.amateurballparkreviewsapi.domain.repository

import com.myapp.amateurballparkreviewsapi.domain.model.Account
import com.myapp.amateurballparkreviewsapi.persistence.entity.AccountEntity
import com.myapp.amateurballparkreviewsapi.persistence.repository.AccountEntityRepository
import org.springframework.stereotype.Repository

@Repository
class AccountRepository(private val entityRepository: AccountEntityRepository) {

    fun registerAccount(account: Account): AccountEntity {
        val entity = AccountEntity(account)
        return entityRepository.save(entity)
    }
}
