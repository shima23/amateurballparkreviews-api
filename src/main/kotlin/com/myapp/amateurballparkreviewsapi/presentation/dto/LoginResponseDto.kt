package com.myapp.amateurballparkreviewsapi.presentation.dto

data class LoginResponseDto(
    val loginResult: Boolean,
    val mailAddress: String,
    val accessKey: String
)
