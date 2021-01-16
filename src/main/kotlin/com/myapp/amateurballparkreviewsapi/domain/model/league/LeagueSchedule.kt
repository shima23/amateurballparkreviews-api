package com.myapp.amateurballparkreviewsapi.domain.model.league

import java.sql.Timestamp

data class LeagueSchedule(
    val id: Int?,
    val leagueId: Int?,
    val eventType: String?,
    val eventDate: String?,
    val eventStartDate: Timestamp?,
    val eventEndDate: Timestamp?,
    val eventTitle: String?,
    val note: String?
)
