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
    val loggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    val client = OkHttpClient.Builder()
        .addInterceptor(loggingInterceptor)
        .build()


    fun retrofit(url: String) : Retrofit =
        Retrofit.Builder()
            .client(client)
            .baseUrl(url)
            .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
            .build()

    inline fun <reified T, B> create(url: B): T = retrofit(url.toString()).create(T::class.java)

}

object ServicePool {
    val authService = ApiFactory.create<AuthService, String>(BuildConfig.AUTH_BASE_URL)
    val userService = ApiFactory.create<UserService, String>(BuildConfig.AUTH_BASE_URL)
    val friendService = ApiFactory.create<FriendService, String>(BuildConfig.FRIEND_BASE_URL)
}