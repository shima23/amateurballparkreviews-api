package com.myapp.amateurballparkreviewsapi.persistence.repository

import com.myapp.amateurballparkreviewsapi.persistence.entity.league.LeagueScoreEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface LeagueScoreEntityRepository : JpaRepository<LeagueScoreEntity, Int>, JpaSpecificationExecutor<LeagueScoreEntity> {

}
