package com.myapp.amateurballparkreviewsapi.domain.model

import java.util.*

data class LeagueScore(
    val id: Int?,
    val leagueId: Int?,
    val year: Int?,
    val homeTeamId: Int?,
    val visitorTeamId: Int?,
    val score: String?,
    val gameDate: Date?
)
