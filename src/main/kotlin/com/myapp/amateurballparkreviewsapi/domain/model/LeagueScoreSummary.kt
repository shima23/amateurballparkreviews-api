package com.myapp.amateurballparkreviewsapi.domain.model

data class LeagueScoreSummary(
    val year: Int,
    val Score: List<LeagueScore>?
)
