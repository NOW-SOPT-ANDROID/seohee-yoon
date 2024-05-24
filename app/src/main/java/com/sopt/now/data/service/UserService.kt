package com.sopt.now.data.service

import com.sopt.now.util.KeyStorage.INFO
import com.sopt.now.util.KeyStorage.MEMBER
import com.sopt.now.util.KeyStorage.MEMBER_ID
import com.sopt.now.data.model.response.ResponseUserDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header

interface UserService {
    @GET("$MEMBER/$INFO")
    fun getUser(
        @Header(MEMBER_ID) userId: String
    ): Call<ResponseUserDto>
}