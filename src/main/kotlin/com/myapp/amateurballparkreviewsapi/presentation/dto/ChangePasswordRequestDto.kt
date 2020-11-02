package com.myapp.amateurballparkreviewsapi.presentation.dto

data class ChangePasswordRequestDto(
    val userId: Int,
    val oldPassword: String,
    val newPassword: String
)
