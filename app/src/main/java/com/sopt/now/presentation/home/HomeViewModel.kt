package com.sopt.now.presentation.home

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.data.Friend
import com.sopt.now.data.ServicePool
import com.sopt.now.data.model.response.ResponseFriendDto
import com.sopt.now.data.model.response.ResponseUserDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel() : ViewModel() {
    val friendList: MutableLiveData<List<Friend>> by lazy {
        MutableLiveData<List<Friend>>()
    }

    val userData: MutableLiveData<ResponseUserDto> by lazy {
        MutableLiveData<ResponseUserDto>()
    }

    fun getFriendData() {
        ServicePool.friendService.getFriend(2).enqueue(object : Callback<ResponseFriendDto> {
            override fun onResponse(
                call: Call<ResponseFriendDto>,
                response: Response<ResponseFriendDto>
            ) {
                if (response.isSuccessful) {
                    val friendData = response.body()?.data
                    friendList.postValue(friendData?.map{
                        friend -> Friend(friend.avatar, friend.firstName, friend.email)
                    })
                } else {
                    Log.d("HomeViewModel", "Failed to load user data: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseFriendDto>, t: Throwable) {
                Log.d("HomeViewModel", "Load friend data error : ${t.message}")
            }

        })
    }

    fun getUserData(userId: String?) {
        ServicePool.userService.getUser(userId).enqueue(object : Callback<ResponseUserDto> {
            override fun onResponse(
                call: Call<ResponseUserDto>,
                response: Response<ResponseUserDto>
            ) {
                if (response.isSuccessful) {
                    val data: ResponseUserDto? = response.body()
                    userData.postValue(data)
                } else {
                    Log.d("HomeViewModel", "Failed to load user data: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ResponseUserDto>, t: Throwable) {
                Log.d("HomeViewModel", "Failed to load user data: ${t.message}")
            }
        })
    }
}