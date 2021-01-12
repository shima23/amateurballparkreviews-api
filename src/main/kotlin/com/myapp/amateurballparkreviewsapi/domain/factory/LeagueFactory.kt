package com.myapp.amateurballparkreviewsapi.domain.factory

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.myapp.amateurballparkreviewsapi.domain.model.league.League
import com.myapp.amateurballparkreviewsapi.domain.model.league.LeagueNotice
import com.myapp.amateurballparkreviewsapi.domain.model.league.LeagueScore
import com.myapp.amateurballparkreviewsapi.domain.model.league.LeagueTeam
import com.myapp.amateurballparkreviewsapi.persistence.entity.league.LeagueEntity
import com.myapp.amateurballparkreviewsapi.persistence.entity.league.LeagueNoticeEntity
import com.myapp.amateurballparkreviewsapi.persistence.entity.league.LeagueScoreEntity
import com.myapp.amateurballparkreviewsapi.persistence.entity.league.LeagueTeamEntity
import com.myapp.amateurballparkreviewsapi.presentation.dto.league.LeagueRegisterRequestDto
import com.myapp.amateurballparkreviewsapi.presentation.dto.league.LeagueScoreRequestDto
import com.myapp.amateurballparkreviewsapi.presentation.dto.league.LeagueUpdateRequestDto
import org.springframework.stereotype.Component
import java.text.SimpleDateFormat

@Component
class LeagueFactory {
    fun createLeagueFromEntity(entity: LeagueEntity): League {
        return League(
            entity.id!!,
            entity.leagueName!!,
            entity.leagueLogo,
            entity.imgUrl1,
            entity.imgUrl2,
            entity.imgUrl3,
            entity.description
        )
    }

    fun createLeagueForRegister(dto: LeagueRegisterRequestDto): League {
        return League(
            id = null,
            leagueName = dto.leagueName,
            leagueLogo = null,
            imgUrl1 = null,
            imgUrl2 = null,
            imgUrl3 = null,
            description = null
        )
    }

    fun createLeagueForUpdate(dto: LeagueUpdateRequestDto): League {
        return League(
            id = dto.id,
            leagueName = dto.leagueName,
            leagueLogo = dto.leagueLogo,
            imgUrl1 = dto.imgUrl1,
            imgUrl2 = dto.imgUrl2,
            imgUrl3 = dto.imgUrl3,
            description = dto.description
        )
    }

    fun createLeagueTeamFromEntity(entity: LeagueTeamEntity): LeagueTeam {
        return LeagueTeam(
            id = entity.id,
            leagueId = entity.leagueId,
            teamName = entity.teamName
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

    fun createLeagueScoreFromEntity(entity: LeagueScoreEntity): LeagueScore {
        return LeagueScore(
            id = entity.id,
            leagueId = entity.leagueId,
            year = entity.year,
            homeTeamId = entity.homeTeamId,
            visitorTeamId = entity.visitorTeamId,
            score = entity.score,
            gameDate = entity.gameDate
        )
    }

    fun createdLeagueNoticeFromEntity(entity: LeagueNoticeEntity): LeagueNotice {
        return LeagueNotice(
            id = entity.id,
            leagueId = entity.leagueId,
            title = entity.title,
            notice = entity.notice,
            createdDate = SimpleDateFormat("yyyy-MM-dd").format(entity.createdDate),
            updatedDate = SimpleDateFormat("yyyy-MM-dd").format(entity.updatedDate)
        )
    }
}
