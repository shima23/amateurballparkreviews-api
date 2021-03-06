package com.myapp.amateurballparkreviewsapi.persistence.repository.league

import com.myapp.amateurballparkreviewsapi.persistence.entity.league.LeagueScoreEntity
import org.springframework.data.jpa.repository.JpaRepository

interface LeagueScoreEntityRepository : JpaRepository<LeagueScoreEntity, Int> {
    fun findByLeagueId(id: Int): List<LeagueScoreEntity>
}
