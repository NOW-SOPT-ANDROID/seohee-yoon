package com.sopt.now.compose.data.service

import com.sopt.now.compose.data.dto.response.ResponseUserDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface UserService {
    @GET("member/info")
    fun getUser(
        @Header("memberId") userId: String?
    ): Call<ResponseUserDto>
}