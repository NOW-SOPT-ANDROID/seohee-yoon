package com.sopt.now.domain.repository

import com.sopt.now.domain.model.AuthData
import retrofit2.Response

interface AuthRepository {
    suspend fun login(id: String, pw: String): Result<Response<Unit>>
    suspend fun signUp(authData: AuthData): Result<Response<Unit>>
}