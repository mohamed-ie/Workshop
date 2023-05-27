package com.example.news.auth.model.source.local

import kotlinx.coroutines.flow.Flow

class FakeAuthLocalDataSource : AuthLocalDataSource{
    override suspend fun saveUserData(userData: UserData) {
        TODO("Not yet implemented")
    }

    override suspend fun resetUserData() {
        TODO("Not yet implemented")
    }

    override suspend fun getUserDataStore(): Flow<Int?> {
        TODO("Not yet implemented")
    }

}