package com.sopt.now.compose.data

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.sopt.now.compose.BuildConfig
import com.sopt.now.compose.data.service.AuthService
import com.sopt.now.compose.data.service.FriendService
import com.sopt.now.compose.data.service.UserService
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

object ApiFactory {
    private const val BASE_URL: String = BuildConfig.AUTH_BASE_URL
    private const val FRIEND_BASE_URL: String = BuildConfig.FRIEND_BASE_URL

    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()

    val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .client(client)
            .baseUrl(BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    val friendRetrofit: Retrofit by lazy {
        Retrofit.Builder()
            .client(client)
            .baseUrl(FRIEND_BASE_URL)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    inline fun <reified T> create(): T = retrofit.create(T::class.java)
    inline fun <reified T> friendCreate(): T = friendRetrofit.create(T::class.java)
}

object ServicePool {
    val authService = ApiFactory.create<AuthService>()
    val userService = ApiFactory.create<UserService>()
    val friendService = ApiFactory.friendCreate<FriendService>()
}