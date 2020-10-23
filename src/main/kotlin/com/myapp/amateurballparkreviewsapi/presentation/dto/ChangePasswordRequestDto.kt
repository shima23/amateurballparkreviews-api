package com.myapp.amateurballparkreviewsapi.presentation.dto

data class ChangePasswordRequestDto(
    val accountId: Int,
    val oldPassword: String,
    val newPassword: String
)
