package com.sopt.now.compose

import androidx.annotation.DrawableRes


data class Friend(
    @DrawableRes val profileImage: Int,
    val name: String,
    val phone: String
)