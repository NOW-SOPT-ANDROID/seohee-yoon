package com.sopt.now.presentation.login

data class LoginState(
    val isSuccess: Boolean,
    val userId: String?,
    val message: String
)