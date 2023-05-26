package com.example.news.auth.model.repository

interface AuthRepository {
    suspend fun login(email:String,  password:String)
    suspend fun signUp(email:String,  password:String, displayName: String)
    fun saveUserData(userID: Int, name:String, email:String)
    fun logout()



}