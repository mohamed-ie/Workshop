package com.example.news.auth.model.source.remote

import com.example.news.auth.model.source.remote.body.SignInBody
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
        const val BASE_URL = "https://identitytoolkit.googleapis.com/"
    }

    @POST("/v1/accounts:signInWithPassword")
    suspend fun login(
        @Body signInBody: SignInBody
    ) : AuthDto

    @POST("/v1/accounts:signUp")
    suspend fun signup(
       @Body signupBody: SignupBody
    ): AuthDto
}