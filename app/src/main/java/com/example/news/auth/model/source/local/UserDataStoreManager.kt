package com.example.news.auth.model.source.local

import kotlinx.coroutines.flow.Flow

interface UserDataStoreManager {
    suspend fun saveUserData(userData: UserData)

    suspend fun resetUserData()

    suspend fun getUserDataStore(): Flow<Int?>
}