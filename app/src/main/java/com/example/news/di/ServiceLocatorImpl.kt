package com.example.news.di

import android.content.Context
import android.net.ConnectivityManager
import androidx.datastore.preferences.preferencesDataStore
import com.example.news.BuildConfig
import com.example.news.auth.model.repository.AuthRepository
import com.example.news.auth.model.repository.AuthRepositoryImpl
import com.example.news.auth.model.source.local.AuthLocalDataSourceImpl
import com.example.news.auth.model.source.local.UserDataStoreManagerImpl
import com.example.news.auth.model.source.remote.AuthRemoteDataSourceImpl
import com.example.news.auth.model.source.remote.AuthWebservice
import com.example.news.auth.model.source.remote.interceptor.AuthInterceptor
import kotlinx.coroutines.Dispatchers
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


        val remoteDataSource = AuthRemoteDataSourceImpl(authWebservice, Dispatchers.IO)
        val localDataSource = AuthLocalDataSourceImpl(dataStoreManager = UserDataStoreManagerImpl(context.dataStore))
        AuthRepositoryImpl(remoteDataSource,localDataSource,Dispatchers.Default)
    }
    override val connectivityManager: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

}