package com.example.news.auth.model.source.remote

import com.example.news.auth.model.source.remote.dto.SignupDto
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthWebservice {
    companion object {
        const val BASE_URL = "https://identitytoolkit.googleapis.com/v1/"
    }

    @POST("accounts:signInWithPassword")
    suspend fun login(
        @Body email: String,
        @Body password: String,
        @Body returnSecureToken: Boolean = true,
    )

    @POST("accounts:signUp")
    suspend fun signup(
        @Body email: String,
        @Body password: String,
        @Body displayName: String,
        @Body returnSecureToken: Boolean = true,
    ): SignupDto
}