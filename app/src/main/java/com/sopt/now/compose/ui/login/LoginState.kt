package com.sopt.now.compose.ui.login

data class LoginState(
    val isSuccess: Boolean,
    val userId: String?,
    val message: String
)
