package com.myapp.amateurballparkreviewsapi.presentation.dto

data class ChangePasswordRequestDto(
    val oldPassword: String,
    val newPassword: String
)
