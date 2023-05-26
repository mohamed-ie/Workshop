package com.example.news.auth.model.source.remote

interface AuthWebservice {
    suspend fun login (email:String,  password:String)
    suspend fun signup (email:String,  password:String, displayName: String)
}