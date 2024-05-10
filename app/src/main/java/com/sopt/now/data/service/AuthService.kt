package com.sopt.now.data.service

import com.sopt.now.data.model.request.RequestLoginDto
import com.sopt.now.data.model.request.RequestSignUpDto
import com.sopt.now.util.KeyStorage.JOIN
import com.sopt.now.util.KeyStorage.LOGIN
import com.sopt.now.util.KeyStorage.MEMBER
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {
    @POST("$MEMBER/$JOIN")
    suspend fun signUp(
        @Body request: RequestSignUpDto,
    ): Response<Unit> // Response에도 code와 message가 있으므로

    @POST("$MEMBER/$LOGIN")
    suspend fun login(
        @Body request: RequestLoginDto,
    ): Response<Unit>
}