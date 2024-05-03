package com.sopt.now.compose.data.service

import com.sopt.now.compose.data.dto.request.RequestSignUpDto
import com.sopt.now.compose.data.dto.response.ResponseSignUpDto
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("member/join")
    fun signUp(
        @Body request: RequestSignUpDto,
    ): Call<ResponseSignUpDto>
}