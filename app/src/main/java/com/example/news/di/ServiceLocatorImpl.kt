package com.example.news.di

import com.example.news.BuildConfig
import com.example.news.auth.model.repository.AuthRepository
import com.example.news.auth.model.repository.AuthRepositoryImpl
import com.example.news.auth.model.source.remote.interceptor.AuthInterceptor
import com.example.news.auth.model.source.remote.AuthWebservice
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceLocatorImpl : ServiceLocator {
    private val retrofitBuilder by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
    }

    override val authRepository: AuthRepository by lazy {
        val authInterceptor = AuthInterceptor(BuildConfig.AUTH_API_KEY)

        val client = OkHttpClient.Builder()
            .addNetworkInterceptor(authInterceptor)
            .build()

        val authWebservice :AuthWebservice = retrofitBuilder
            .client(client)
            .baseUrl(AuthWebservice.BASE_URL)
            .build()
            .create(AuthWebservice::class.java)


        AuthRepositoryImpl()
    }
}