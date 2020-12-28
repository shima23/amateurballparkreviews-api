package com.myapp.amateurballparkreviewsapi.presentation.dto

import com.myapp.amateurballparkreviewsapi.domain.model.Score
import java.util.*

data class LeagueScoreRequestDto(
    val leagueId: Int,
    val year: Int,
    val homeTeamId: Int,
    val visitorTeamId: Int,
    val score: Score,
    val gameDate: Date
)
