package com.sopt.now.ui.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sopt.now.data.ServicePool
import com.sopt.now.data.User
import com.sopt.now.data.dto.request.RequestSignUpDto
import com.sopt.now.data.dto.response.ResponseSignUpDto
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel : ViewModel() {
    private val authService by lazy { ServicePool.authService }

    val liveData = MutableLiveData<SignUpState>()

    private var user: User? = null

    fun signUp(request: RequestSignUpDto) {
        user = User(
            id = request.authenticationId,
            password = request.password,
            name = request.nickname,
            phone = request.phone
        )

        authService.signUp(request).enqueue(object : Callback<ResponseSignUpDto> {
            override fun onResponse(
                call: Call<ResponseSignUpDto>,
                response: Response<ResponseSignUpDto>,
            ) {
                if (response.isSuccessful) {
                    val data: ResponseSignUpDto? = response.body()
                    val userId = response.headers()["location"]
                    liveData.value = SignUpState(
                        isSuccess = true,
                        message = "회원가입 성공! 유저의 ID는 $userId 입니다"
                    )
                } else {
                    val errorBody = response.errorBody()?.string() ?: "No error message"
                    val errorMessage = JSONObject(errorBody).getString("message")

                    liveData.value = SignUpState(
                        isSuccess = false,
                        message = errorMessage
                    )
                }
            }

            override fun onFailure(call: Call<ResponseSignUpDto>, t: Throwable) {
                liveData.value = SignUpState(
                    isSuccess = false,
                    message = "서버에러"
                )
            }
        })
    }
}