package com.sopt.now.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sopt.now.presentation.home.HomeViewModel
import com.sopt.now.presentation.login.LoginViewModel
import com.sopt.now.presentation.signup.SignUpViewModel

class ViewModelFactory :  ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when (modelClass) {
            LoginViewModel::class.java -> {
                val repository = AuthRepoImpl(ServicePool.authService)
                return LoginViewModel(repository) as T
            }
            SignUpViewModel::class.java -> {
                val repository = AuthRepoImpl(ServicePool.authService)
                return SignUpViewModel(repository) as T
            }
//            HomeViewModel::class.java -> HomeViewModel()
            else -> throw IllegalArgumentException("Failed to create ViewModel: ${modelClass.name}")
        }
    }
}