package com.sopt.now.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sopt.now.domain.repository.AuthRepository
import com.sopt.now.util.KeyStorage.USER_PREF
import com.sopt.now.util.MainApplication
import kotlinx.coroutines.launch
import retrofit2.HttpException
import retrofit2.Response

class LoginViewModel(private val authRepository: AuthRepository) : ViewModel() {
    private val _loginData = MutableLiveData<LoginState>()
    val loginData: LiveData<LoginState> = _loginData

    fun login(authData: Pair<String, String>) {
        viewModelScope.launch {
            authRepository.login(
                id = authData.first,
                pw = authData.second
            )
                .onSuccess { response: Response<Unit> ->
                    val userId = response.headers()["location"]

                    _loginData.value = LoginState(
                        isSuccess = true,
                        message = "$userId 님 환영합니다!",
                        userId = userId
                    )

                    MainApplication.prefsManager.setString(USER_PREF, userId)
                }
                .onFailure {
                    if(it is HttpException){

                    }else {

                    }
                    _loginData.value = LoginState(
                        isSuccess = false,
                        message = "서버 에러",
                        userId = null
                    )
                }
        }
    }
}