package com.sopt.now.compose.ui.home

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.sopt.now.compose.data.Friend
import com.sopt.now.compose.data.ServicePool
import com.sopt.now.compose.data.dto.response.ResponseFriendDto
import com.sopt.now.compose.data.dto.response.ResponseUserDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeViewModel : ViewModel() {
    val userData: MutableState<ResponseUserDto.UserData?> = mutableStateOf(null)
    val friendList: MutableState<List<Friend>> = mutableStateOf(emptyList())

    fun getUserData(userId: String) {
        ServicePool.userService.getUser(userId).enqueue(object : Callback<ResponseUserDto> {
            override fun onResponse(
                call: Call<ResponseUserDto>,
                response: Response<ResponseUserDto>
            ) {
                if (response.isSuccessful) {
                    val data: ResponseUserDto? = response.body()
                    userData.value = data?.data

                    Log.d("MyPageViewModel", "User data: ${userData.value}")
                } else {
                    Log.d("MyPageViewModel", "Failed to load user data: $userId")
                }
            }

            override fun onFailure(call: Call<ResponseUserDto>, t: Throwable) {
                Log.d("MyPageViewModel", "Failed to load user data: ${t.message}")
            }
        })
    }

    fun getFriendData() {
        if (friendList.value.isEmpty()) {
            ServicePool.friendService.getFriend(2).enqueue(object : Callback<ResponseFriendDto> {
                override fun onResponse(
                    call: Call<ResponseFriendDto>,
                    response: Response<ResponseFriendDto>
                ) {
                    if(response.isSuccessful) {
                        val friend = response.body()?.data

                        friend?.forEach { friendData ->
                            friendList.value += Friend(
                                friendData.avatar, friendData.firstName, friendData.email
                            )
                        }
                    } else {
                        Log.d("HomeViewModel", "Failed to load user data: ${response.message()}")
                    }
                }

                override fun onFailure(call: Call<ResponseFriendDto>, t: Throwable) {
                    Log.d("HomeViewModel", "Load friend data error : ${t.message}")
                }
            })
        }
    }
}
