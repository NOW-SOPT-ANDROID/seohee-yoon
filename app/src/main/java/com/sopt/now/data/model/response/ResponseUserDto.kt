package com.sopt.now.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseUserDto(
    @SerialName("code")
    val code: Int,
    @SerialName("data")
    val data: UserData,
    @SerialName("message")
    val message: String
) {
    @Serializable
    data class UserData(
        @SerialName("authenticationId")
        val authenticationId: String,
        @SerialName("nickname")
        val nickname: String,
        @SerialName("phone")
        val phone: String
    )
}
