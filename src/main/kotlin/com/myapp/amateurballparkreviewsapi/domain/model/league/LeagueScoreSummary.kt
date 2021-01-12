package com.myapp.amateurballparkreviewsapi.domain.model.league

data class LeagueScoreSummary(
    val year: Int,
    val Score: List<LeagueScore>?
)
