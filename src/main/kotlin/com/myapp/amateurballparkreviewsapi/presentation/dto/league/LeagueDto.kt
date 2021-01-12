package com.myapp.amateurballparkreviewsapi.presentation.dto.league

import com.myapp.amateurballparkreviewsapi.domain.model.league.League
import com.myapp.amateurballparkreviewsapi.domain.model.league.LeagueNotice
import com.myapp.amateurballparkreviewsapi.domain.model.league.LeagueScoreSummary
import com.myapp.amateurballparkreviewsapi.domain.model.league.LeagueTeam

data class LeagueDto(
    val league: League,
    val leagueTeam: List<LeagueTeam>?,
    val leagueScoreSummary: List<LeagueScoreSummary>?,
    val leagueNotice: List<LeagueNotice>?
)
