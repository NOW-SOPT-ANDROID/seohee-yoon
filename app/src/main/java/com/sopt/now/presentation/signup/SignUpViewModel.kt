package com.sopt.now.presentation.signup

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.domain.model.AuthData
import com.sopt.now.domain.repository.AuthRepository
import kotlinx.coroutines.launch
import org.json.JSONObject
import retrofit2.Response

class SignUpViewModel(private val authRepository: AuthRepository) : ViewModel() {
    private val _signUpData = MutableLiveData<SignUpState>() // 외부에서 접근 못하게 backing property
    val liveData: LiveData<SignUpState> = _signUpData

    fun signUp(authData: AuthData) {
        viewModelScope.launch {
            Log.e("여기", "viewmodelscope")
            authRepository.signUp(
                authData = authData
            )
                .onSuccess { response: Response<Unit> ->
                    val userId = response.headers()["location"]
                    if (response.isSuccessful) {
                        _signUpData.value = SignUpState(
                            isSuccess = true,
                            message = "회원가입 성공! 유저의 ID는 $userId 입니다"
                        )
                    }

                    else {
                        val errorBody = response.errorBody()?.string() ?: "No error message"
                        val errorMessage = JSONObject(errorBody).getString("message")
                        _signUpData.value = SignUpState(
                            isSuccess = false,
                            message = errorMessage
                        )

                    }
                }

                .onFailure {
                    Log.e("throw", it.message.toString())
                    _signUpData.value = it.message?.let { it1 ->
                        SignUpState(
                            isSuccess = false,
                            message = it1
                        )
                    }
                }
        }
    }
}