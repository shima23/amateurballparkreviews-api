package com.myapp.amateurballparkreviewsapi.persistence.repository

import com.myapp.amateurballparkreviewsapi.persistence.entity.AccountEntity
import org.springframework.data.jpa.repository.JpaRepository

interface AccountEntityRepository : JpaRepository<AccountEntity, Int> {

}
