package com.myapp.amateurballparkreviewsapi.domain.model.league

data class LeagueNotice(
    val id: Int?,
    val leagueId: Int?,
    val title: String?,
    val notice: String?,
    val createdDate: String?,
    val updatedDate: String?
)
