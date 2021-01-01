package com.myapp.amateurballparkreviewsapi.presentation.dto.league

import com.myapp.amateurballparkreviewsapi.domain.model.League
import com.myapp.amateurballparkreviewsapi.domain.model.LeagueScoreSummary
import com.myapp.amateurballparkreviewsapi.domain.model.LeagueTeam

data class LeagueDto(
    val league: League,
    val leagueTeam: List<LeagueTeam>?,
    val leagueScoreSummary: List<LeagueScoreSummary>?
)
