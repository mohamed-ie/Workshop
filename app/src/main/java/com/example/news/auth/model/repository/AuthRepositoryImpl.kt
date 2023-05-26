package com.example.news.auth.model.repository

import com.example.news.auth.model.source.local.AuthLocalDataSource
import com.example.news.auth.model.source.local.UserData
import com.example.news.auth.model.source.remote.AuthWebservice

class AuthRepositoryImpl(var authWebservice: AuthWebservice,
                         var authLocalDataSource: AuthLocalDataSource) : AuthRepository {
    override suspend fun login(email: String, password: String) {
        authWebservice.login(email, password)
    }
    override suspend fun signUp(email: String, password: String, displayName: String) {
        authWebservice.signup(email, password, displayName)
    }
    override suspend fun saveUserData(userID: Int, name: String, email: String) {
      authLocalDataSource.saveUserData(UserData(userID,name,email))
    }
    override suspend fun logout() {
        authLocalDataSource.resetUserData()
    }
}