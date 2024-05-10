package com.sopt.now.presentation.login

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.data.model.request.RequestLoginDto
import com.sopt.now.data.model.response.ResponseLoginDto
import com.sopt.now.data.repository.AuthRepository
import com.sopt.now.util.KeyStorage.USER_PREF
import com.sopt.now.util.MainApplication
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response

class LoginViewModel(private val authRepository: AuthRepository) : ViewModel() {
    private val _liveData = MutableLiveData<LoginState>()
    val liveData: LiveData<LoginState> = _liveData

    fun login(request: RequestLoginDto) {
        viewModelScope.launch {
            authRepository.login(request)
                .onSuccess { response: Response<ResponseLoginDto> ->
                    val userId = response.headers()["location"]

                    _liveData.value = LoginState(
                        isSuccess = true,
                        message = "$userId 님 환영합니다!",
                        userId = userId
                    )

                    MainApplication.prefsManager.setString(USER_PREF, userId)
                    Log.d("seohee",
                        MainApplication.prefsManager.setString(USER_PREF, userId).toString()
                    )
                }
                .onFailure {
                    if(it is HttpException){

                    }else {

                    }
                    _liveData.value = LoginState(
                        isSuccess = false,
                        message = "서버 에러",
                        userId = null
                    )
                }
        }
    }
}