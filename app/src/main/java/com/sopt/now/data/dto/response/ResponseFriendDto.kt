package com.sopt.now.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResponseFriendDto(
    @SerialName("data")
    val `data`: List<Data>,
    @SerialName("page")
    val page: Int,
    @SerialName("per_page")
    val per_page: Int,
    @SerialName("support")
    val support: Support,
    @SerialName("total")
    val total: Int,
    @SerialName("total_pages")
    val total_pages: Int
) {
    @Serializable
    data class Data(
        @SerialName("avatar")
        val avatar: String,
        @SerialName("email")
        val email: String,
        @SerialName("first_name")
        val first_name: String,
        @SerialName("id")
        val id: Int,
        @SerialName("last_name")
        val last_name: String
    )

    @Serializable
    data class Support(
        @SerialName("text")
        val text: String,
        @SerialName("url")
        val url: String
    )
}