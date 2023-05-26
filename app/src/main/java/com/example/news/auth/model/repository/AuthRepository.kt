package com.example.news.auth.model.repository

interface AuthRepository {
    suspend fun login(email:String,  password:String)
    suspend fun signUp(email:String,  password:String, displayName: String)
    suspend fun saveUserData(userID: Int, name:String, email:String)
    suspend fun logout()



}