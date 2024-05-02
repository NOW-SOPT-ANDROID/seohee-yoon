package com.sopt.now.data.service

import com.sopt.now.data.dto.response.ResponseUserDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface UserService {
    @GET("member/info")
    fun getUser(
        @Header("memberId") userId: Int
    ): Call<ResponseUserDto>
}