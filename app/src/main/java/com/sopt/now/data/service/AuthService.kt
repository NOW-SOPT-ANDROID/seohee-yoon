package com.sopt.now.data.service

import com.sopt.now.util.KeyStorage.JOIN
import com.sopt.now.util.KeyStorage.LOGIN
import com.sopt.now.util.KeyStorage.MEMBER
import com.sopt.now.data.model.request.RequestLoginDto
import com.sopt.now.data.model.request.RequestSignUpDto
import com.sopt.now.data.model.response.ResponseLoginDto
import com.sopt.now.data.model.response.ResponseSignUpDto
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("$MEMBER/$JOIN")
    fun signUp(
        @Body request: RequestSignUpDto,
    ): Call<ResponseSignUpDto>

    @POST("$MEMBER/$LOGIN")
    suspend fun login(
        @Body request: RequestLoginDto,
    ): Response<ResponseLoginDto>
}