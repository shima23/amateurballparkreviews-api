package com.myapp.amateurballparkreviewsapi.presentation.dto.league

data class LeagueUpdateRequestDto(
    val id: Int,
    val leagueName: String?,
    val leagueLogo: String?,
    val imgUrl1: String?,
    val imgUrl2: String?,
    val imgUrl3: String?,
    val description: String?
)
