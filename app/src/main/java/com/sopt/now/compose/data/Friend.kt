package com.sopt.now.compose.data

import androidx.annotation.DrawableRes


data class Friend(
    @DrawableRes val profileImage: Int,
    val name: String,
    val phone: String
)