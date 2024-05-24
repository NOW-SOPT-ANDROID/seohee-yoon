package com.sopt.now.data.service

import com.sopt.now.util.KeyStorage.PAGE
import com.sopt.now.util.KeyStorage.USERS
import com.sopt.now.data.model.response.ResponseFriendDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface FriendService {
    @GET(USERS)
    fun getFriend(
        @Query(PAGE) page: Int
    ) : Call<ResponseFriendDto>
}