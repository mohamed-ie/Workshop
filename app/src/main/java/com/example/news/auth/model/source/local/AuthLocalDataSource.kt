package com.example.news.auth.model.source.local

interface AuthLocalDataSource {
    suspend fun saveUserData(userData: UserData)
    suspend fun resetUserData()
    suspend fun getUserDataStore(): Any
}