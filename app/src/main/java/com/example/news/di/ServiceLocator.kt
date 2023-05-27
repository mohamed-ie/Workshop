package com.example.news.di

import com.example.news.auth.model.repository.AuthRepository
import com.example.news.news.model.repository.NewsRepository

interface ServiceLocator {
    val authRepository: AuthRepository
    val newsRepository : NewsRepository
}