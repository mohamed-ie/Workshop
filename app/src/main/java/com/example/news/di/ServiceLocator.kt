package com.example.news.di

import android.net.ConnectivityManager
import com.example.news.auth.model.repository.AuthRepository

interface ServiceLocator {
    val authRepository: AuthRepository
    val connectivityManager : ConnectivityManager
}