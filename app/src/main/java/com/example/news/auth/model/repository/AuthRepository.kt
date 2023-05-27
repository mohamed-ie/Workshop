package com.example.news.auth.model.repository

import com.example.news.auth.model.source.local.model.SignupInfo
import com.example.news.helpers.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    fun login(email: String, password: String):Flow<Resource<SignupInfo>>
    fun signUp(email:String,  password:String, displayName: String) : Flow<Resource<SignupInfo>>
    suspend fun saveUserData(userID: Int, name:String, email:String)
    suspend fun logout()
    suspend fun getUserData(): Flow<Int?>


}