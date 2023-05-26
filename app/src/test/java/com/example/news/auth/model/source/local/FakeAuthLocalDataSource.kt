package com.example.news.auth.model.source.local

class FakeAuthLocalDataSource : AuthLocalDataSource{
    override suspend fun saveUserData(userData: UserData) {
        TODO("Not yet implemented")
    }

    override suspend fun resetUserData() {
        TODO("Not yet implemented")
    }

    override suspend fun getUserDataStore(): Any {
        TODO("Not yet implemented")
    }

}