package com.myapp.amateurballparkreviewsapi.persistence.repository

import com.myapp.amateurballparkreviewsapi.persistence.entity.league.LeagueEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor

interface LeagueEntityRepository : JpaRepository<LeagueEntity, Int>, JpaSpecificationExecutor<LeagueEntity> {

}
