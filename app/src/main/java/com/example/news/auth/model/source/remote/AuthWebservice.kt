package com.example.news.auth.model.source.remote

import com.example.news.auth.model.source.remote.body.SignupBody
import com.example.news.auth.model.source.remote.dto.AuthDto
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface AuthWebservice {
    companion object {
        const val BASE_URL = "https://identitytoolkit.googleapis.com/v1/"
    }

    @POST("accounts:signInWithPassword")
    suspend fun login(
        @Body email: String,
        @Body password: String,
        @Body returnSecureToken: Boolean = true,
    ) : AuthDto

    @POST("./accounts:signUp")
    suspend fun signup(
       @Body signupBody: SignupBody
    ): AuthDto
}