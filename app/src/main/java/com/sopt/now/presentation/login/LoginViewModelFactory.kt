package com.sopt.now.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sopt.now.data.ServicePool
import com.sopt.now.data.repository.AuthRepository

class LoginViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) { //modelClass가 LoginViewModel인지 확인
            val repository = AuthRepository(ServicePool.authService) //authService를 인자로 받아서 Repository인스턴스 생성
            return LoginViewModel(repository) as T
        } else {
            throw IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
        }
    }
}