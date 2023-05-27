package com.example.news.auth.model.repository

import com.example.news.auth.model.source.local.model.SignupInfo
import com.example.news.helpers.Resource
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(email:String,  password:String)
    fun signUp(email:String,  password:String, displayName: String) : Flow<Resource<SignupInfo>>
    suspend fun saveUserData(userID: Int, name:String, email:String)
    suspend fun logout()



}