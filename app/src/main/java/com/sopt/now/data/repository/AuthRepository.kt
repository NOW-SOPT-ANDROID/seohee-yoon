package com.sopt.now.data.repository

import com.sopt.now.data.model.request.RequestLoginDto
import com.sopt.now.data.model.response.ResponseLoginDto
import com.sopt.now.data.service.AuthService
import retrofit2.Response

class AuthRepository(
    private val authService: AuthService
) {
    suspend fun login(request: RequestLoginDto): Result<Response<ResponseLoginDto>> =
        runCatching {
            authService.login(request)
        }
}