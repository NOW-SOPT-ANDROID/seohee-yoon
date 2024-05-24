package com.sopt.now.compose.ui.mypage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.compose.data.ServicePool
import com.sopt.now.compose.data.dto.response.ResponseUserDto
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageViewModel() : ViewModel() {
    private val _userData = MutableLiveData<ResponseUserDto.UserData>()
    val userData: LiveData<ResponseUserDto.UserData> = _userData

    fun getUserData(userId: String) {
        ServicePool.userService.getUser(userId).enqueue(object : Callback<ResponseUserDto> {
            override fun onResponse(
                call: Call<ResponseUserDto>,
                response: Response<ResponseUserDto>
            ) {
                if (response.isSuccessful) {
                    _userData.value = response.body()?.data

                    Log.d("MyPageViewModel", "User data: ${_userData.value}")
                } else {
                    Log.d("MyPageViewModel", "Failed to load user data: $userId")
                }
            }

            override fun onFailure(call: Call<ResponseUserDto>, t: Throwable) {
                Log.d("MyPageViewModel", "Failed to load user data: ${t.message}")
            }
        })
    }
}