package com.example.news.di

import android.net.ConnectivityManager
import com.example.news.auth.model.repository.AuthRepository
import com.example.news.news.model.repository.NewsRepository

interface ServiceLocator {
    val authRepository: AuthRepository
    val newsRepository : NewsRepository
    val connectivityManager : ConnectivityManager
}