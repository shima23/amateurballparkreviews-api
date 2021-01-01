package com.myapp.amateurballparkreviewsapi.persistence.repository.league

import com.myapp.amateurballparkreviewsapi.persistence.entity.league.LeagueTeamEntity
import org.springframework.data.jpa.repository.JpaRepository

interface LeagueTeamEntityRepository : JpaRepository<LeagueTeamEntity, Int> {
    fun findByLeagueId(id: Int): List<LeagueTeamEntity>
}
