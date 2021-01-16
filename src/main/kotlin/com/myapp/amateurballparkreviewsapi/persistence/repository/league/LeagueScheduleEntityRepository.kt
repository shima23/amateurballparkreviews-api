package com.myapp.amateurballparkreviewsapi.persistence.repository.league

import com.myapp.amateurballparkreviewsapi.persistence.entity.league.LeagueScheduleEntity
import org.springframework.data.jpa.repository.JpaRepository

interface LeagueScheduleEntityRepository : JpaRepository<LeagueScheduleEntity, Int> {
    fun findByLeagueId(id: Int): List<LeagueScheduleEntity>
}
