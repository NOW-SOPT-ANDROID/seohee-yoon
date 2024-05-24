package com.sopt.now.data

import com.sopt.now.data.model.request.RequestLoginDto
import com.sopt.now.data.model.request.RequestSignUpDto
import com.sopt.now.data.service.AuthService
import com.sopt.now.domain.repository.AuthRepository
import com.sopt.now.domain.model.AuthData
import retrofit2.Response

class AuthRepoImpl(private val authService: AuthService) : AuthRepository {
    override suspend fun login(id: String, pw: String): Result<Response<Unit>> = runCatching {
        authService.login(
            RequestLoginDto(
                authenticationId = id,
                password = pw
            )
        )
    }

    override suspend fun signUp(authData: AuthData): Result<Response<Unit>> = runCatching {
        authService.signUp(
            RequestSignUpDto(
                authenticationId = authData.id,
                password = authData.pw,
                nickname = authData.nickname,
                phone = authData.phone
            )
        )
    }
}