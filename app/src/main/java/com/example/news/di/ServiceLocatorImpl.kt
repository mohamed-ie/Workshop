package com.example.news.di

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.example.news.BuildConfig
import com.example.news.auth.model.repository.AuthRepository
import com.example.news.auth.model.repository.AuthRepositoryImpl
import com.example.news.auth.model.source.local.AuthLocalDataSourceImpl
import com.example.news.auth.model.source.local.UserDataStoreManagerImpl
import com.example.news.auth.model.source.remote.AuthWebservice
import com.example.news.auth.model.source.remote.interceptor.AuthInterceptor
import com.example.news.news.model.repository.NewsRepository
import com.example.news.news.model.repository.NewsRepositoryImpl
import com.example.news.news.model.source.remote.NewsWebservice
import com.example.news.news.model.source.remote.interceptor.NewsInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServiceLocatorImpl(private val context: Context) : ServiceLocator {

    companion object {
        const val SHARED_PREFERENCES_NAME = "USER"
    }

    private val retrofitBuilder by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
    }

    private val Context.dataStore by preferencesDataStore(
        name = SHARED_PREFERENCES_NAME
    )

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
        AuthRepositoryImpl(authWebservice,AuthLocalDataSourceImpl(UserDataStoreManagerImpl(dataStore = context.dataStore)))
    }

    override val newsRepository: NewsRepository by lazy {
        val newsInterceptor = NewsInterceptor(BuildConfig.NEWS_API_KEY)
        val client = OkHttpClient.Builder()
            .addNetworkInterceptor(newsInterceptor)
            .build()

        val newsWebservice :NewsWebservice = retrofitBuilder
            .client(client)
            .baseUrl(NewsWebservice.BASE_URL)
            .build()
            .create(NewsWebservice::class.java)

        NewsRepositoryImpl()
    }



}