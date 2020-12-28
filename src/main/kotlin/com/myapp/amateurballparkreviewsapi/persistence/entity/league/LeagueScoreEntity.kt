package com.myapp.amateurballparkreviewsapi.persistence.entity.league

import com.myapp.amateurballparkreviewsapi.domain.model.LeagueScore
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "LEAGUE_SCORE")
data class LeagueScoreEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    var id: Int? = null,

    @Column(name = "LEAGUE_ID", nullable = false)
    var leagueId: Int? = null,

    @Column(name = "YEAR", nullable = false)
    var year: Int? = null,

    @Column(name = "HOME_TEAM_ID", nullable = false)
    var homeTeamId: Int? = null,

    @Column(name = "VISITOR_TEAM_ID", nullable = false)
    var visitorTeamId: Int? = null,

    @Column(name = "SCORE", nullable = false, columnDefinition = "TEXT")
    var score: String? = null,

    @Column(name = "GAME_DATE", nullable = false)
    var gameDate: Date? = null

) {
    constructor(leagueScore: LeagueScore) : this(
        leagueScore.id,
        leagueScore.leagueId,
        leagueScore.year,
        leagueScore.homeTeamId,
        leagueScore.visitorTeamId,
        leagueScore.score,
        leagueScore.gameDate
    )
}