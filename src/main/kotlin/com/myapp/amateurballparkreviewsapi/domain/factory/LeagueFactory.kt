package com.myapp.amateurballparkreviewsapi.domain.factory

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.myapp.amateurballparkreviewsapi.domain.model.League
import com.myapp.amateurballparkreviewsapi.domain.model.LeagueScore
import com.myapp.amateurballparkreviewsapi.persistence.entity.league.LeagueEntity
import com.myapp.amateurballparkreviewsapi.presentation.dto.LeagueRegisterRequestDto
import com.myapp.amateurballparkreviewsapi.presentation.dto.LeagueScoreRequestDto
import org.springframework.stereotype.Component

@Component
class LeagueFactory {
    fun createLeagueFromEntity(entity: LeagueEntity): League {
        return League(
            entity.id!!,
            entity.leagueName!!,
            entity.leagueLogo,
            entity.imgUrl1,
            entity.imgUrl2,
            entity.imgUrl3
        )
    }

    fun createLeague(dto: LeagueRegisterRequestDto): League {
        return League(
            null,
            dto.leagueName,
            null,
            null,
            null,
            null
        )
    }

    fun createLeagueScore(dto: LeagueScoreRequestDto): LeagueScore {
        return LeagueScore(
            id = null,
            leagueId = dto.leagueId,
            year = dto.year,
            homeTeamId = dto.homeTeamId,
            visitorTeamId = dto.visitorTeamId,
            score = jacksonObjectMapper().writeValueAsString(dto.score),
            gameDate = dto.gameDate
        )
    }
}
