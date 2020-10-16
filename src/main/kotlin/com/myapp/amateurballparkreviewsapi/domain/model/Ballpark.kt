package com.myapp.amateurballparkreviewsapi.domain.model

data class Ballpark(
    val id: Int,
    val name: String,
    val prefectures: String,
    val municipalities: String,
    val rating: String,
    val count: Int
)
