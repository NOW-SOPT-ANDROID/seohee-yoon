package com.sopt.now.compose

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User(
    val id: String,
    val password: String,
    val name: String,
    val phone: String
):Parcelable
