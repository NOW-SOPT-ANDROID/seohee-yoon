package com.sopt.now.ui.login

data class LoginState(
    val isSuccess: Boolean,
    val userId: String?,
    val message: String
)