package com.sopt.now.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.data.ServicePool
import com.sopt.now.data.dto.request.RequestLoginDto
import com.sopt.now.data.dto.response.ResponseLoginDto
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private val authService by lazy { ServicePool.authService }

    val liveData = MutableLiveData<LoginState>()

    fun login(request: RequestLoginDto) {
        authService.login(request).enqueue(object : Callback<ResponseLoginDto> {
            override fun onResponse(
                call: Call<ResponseLoginDto>,
                response: Response<ResponseLoginDto>,
            ) {
                if (response.isSuccessful) {
                    val data: ResponseLoginDto? = response.body()
                    val userId = response.headers()["location"]
                    liveData.value = LoginState(
                        isSuccess = true,
                        message = "$userId 님 환영합니다!"
                    )
                } else {
                    val errorBody = response.errorBody()?.string() ?: "No error message"
                    val errorMessage = JSONObject(errorBody).getString("message")

                    liveData.value = LoginState(
                        isSuccess = false,
                        message = errorMessage
                    )
                }
            }

            override fun onFailure(call: Call<ResponseLoginDto>, t: Throwable) {
                liveData.value = LoginState(
                    isSuccess = false,
                    message = "서버에러"
                )
            }
        })
    }
}