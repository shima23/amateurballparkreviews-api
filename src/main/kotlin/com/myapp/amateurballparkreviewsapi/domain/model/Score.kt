package com.myapp.amateurballparkreviewsapi.domain.model

data class Score(
    var winTeamName: String,
    var winPitcherName: String,
    var losePitcherName: String,
    var homeRunPlayers: List<String>,
    var homeTeamRun: Int,
    var VisitorTeamRun: Int,
    var homeTeamScore: List<String>,
    var visitorTeamScore: List<String>
)
