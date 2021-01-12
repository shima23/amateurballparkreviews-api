package com.myapp.amateurballparkreviewsapi.persistence.repository.league

import com.myapp.amateurballparkreviewsapi.persistence.entity.league.LeagueNoticeEntity
import org.springframework.data.jpa.repository.JpaRepository

interface LeagueNoticeEntityRepository : JpaRepository<LeagueNoticeEntity, Int> {
    fun findByLeagueId(id: Int): List<LeagueNoticeEntity>
}
