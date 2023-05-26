package com.example.news.di

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.example.news.auth.model.repository.AuthRepository

interface ServiceLocator {
    val authRepository: AuthRepository
}