package com.example.news.di

import com.example.news.auth.model.repository.AuthRepository

interface ServiceLocator {
    val authRepository: AuthRepository
}