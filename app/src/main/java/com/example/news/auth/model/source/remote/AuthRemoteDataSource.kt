package com.example.news.auth.model.source.remote

import com.example.news.auth.model.source.remote.body.SignupBody
import com.example.news.auth.model.source.remote.dto.AuthDto
import kotlinx.coroutines.flow.Flow

interface AuthRemoteDataSource {
    fun signup(signupBody: SignupBody): Flow<AuthDto>
}