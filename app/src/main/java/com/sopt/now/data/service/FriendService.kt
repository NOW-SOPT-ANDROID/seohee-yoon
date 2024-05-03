package com.sopt.now.data.service

import com.sopt.now.data.dto.response.ResponseFriendDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FriendService {
    @GET("users")
    fun getFriend(
        @Query("page") page: Int
    ) : Call<ResponseFriendDto>
}