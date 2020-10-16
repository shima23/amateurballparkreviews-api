package com.myapp.amateurballparkreviewsapi.persistence.repository

import org.springframework.data.jpa.repository.JpaRepository
import com.myapp.amateurballparkreviewsapi.persistence.entity.BallparkEntity
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface BallparkEntityRepository : JpaRepository<BallparkEntity, Int>, JpaSpecificationExecutor<BallparkEntity> {
    fun findByName(id: Int): BallparkEntity
}
