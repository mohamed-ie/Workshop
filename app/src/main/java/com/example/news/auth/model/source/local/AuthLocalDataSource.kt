package com.example.news.auth.model.source.local

interface AuthLocalDataSource {
    fun saveUserData(userID: Int, name:String, email:String)

    fun resetUserData()
}