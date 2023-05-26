package com.example.news.auth.model.source.remote

interface AuthWebservice {
    suspend fun login ()
    suspend fun signup ()
}