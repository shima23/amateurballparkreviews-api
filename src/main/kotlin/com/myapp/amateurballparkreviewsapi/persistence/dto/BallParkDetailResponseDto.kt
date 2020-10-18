package com.myapp.amateurballparkreviewsapi.persistence.dto

data class BallParkDetailResponseDto(
    var id: Int,
    var name: String,
    val prefectures: String,
    val municipalities: String,
    val rating: String,
    val count: Int,
    val residence: String,
    // GoogleMapのURL
    val googleMapUrl: String,
    val grassType: Int,
    val groundType: Int
)
