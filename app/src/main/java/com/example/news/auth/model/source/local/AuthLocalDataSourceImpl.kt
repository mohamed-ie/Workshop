package com.example.news.auth.model.source.local

import kotlinx.coroutines.flow.Flow


class AuthLocalDataSourceImpl(
    private val dataStoreManager:UserDataStoreManager
) : AuthLocalDataSource {
    override suspend fun saveUserData(userData: UserData) {
        dataStoreManager.saveUserData(userData)
    }

    override suspend fun resetUserData() {
        dataStoreManager.resetUserData()
    }

    override suspend fun getUserDataStore(): Flow<Int?> = dataStoreManager.getUserDataStore()

}