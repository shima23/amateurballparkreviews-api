package com.myapp.amateurballparkreviewsapi.domain.repository

import com.myapp.amateurballparkreviewsapi.domain.factory.LeagueFactory
import com.myapp.amateurballparkreviewsapi.domain.model.League
import com.myapp.amateurballparkreviewsapi.domain.model.LeagueScore
import com.myapp.amateurballparkreviewsapi.persistence.entity.league.LeagueEntity
import com.myapp.amateurballparkreviewsapi.persistence.entity.league.LeagueScoreEntity
import com.myapp.amateurballparkreviewsapi.persistence.repository.LeagueEntityRepository
import com.myapp.amateurballparkreviewsapi.persistence.repository.LeagueScoreEntityRepository
import org.springframework.stereotype.Repository

@Repository
class LeagueRepository(private val leagueEntityRepository: LeagueEntityRepository,
                       private val leagueScoreEntityRepository: LeagueScoreEntityRepository,
                       private val leagueFactory: LeagueFactory
) {
    /** League */

    fun getLeague(leagueId: Int): League {
        return leagueFactory.createLeagueFromEntity(leagueEntityRepository.findById(leagueId).get())
    }

    fun registerLeague(league: League): League {
        val entity = LeagueEntity(league)
        return leagueFactory.createLeagueFromEntity(leagueEntityRepository.save(entity))
    }

    /** LeagueScore */

    fun registerLeagueScore(leagueScore: LeagueScore) {
        val entity = LeagueScoreEntity(leagueScore)
        leagueScoreEntityRepository.save(entity)
    }


}
