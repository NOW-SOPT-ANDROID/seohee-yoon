package com.sopt.now.ui.mypage

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.data.ServicePool
import com.sopt.now.data.model.response.ResponseUserDto
import com.sopt.now.data.model.response.ResponseUserDto.UserData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyPageViewModel() : ViewModel() {
    var userData = MutableLiveData<UserData?>()

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
}
