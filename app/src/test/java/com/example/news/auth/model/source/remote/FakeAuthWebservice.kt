package com.example.news.auth.model.source.remote

import com.example.news.auth.model.source.remote.body.SignupBody
import com.example.news.auth.model.source.remote.dto.AuthDto


class FakeAuthWebservice :AuthWebservice{
    override suspend fun login(
        email: String,
        password: String,
        returnSecureToken: Boolean
    ): AuthDto {
        TODO("Not yet implemented")
    }

    override suspend fun signup(signupBody: SignupBody): AuthDto {
        TODO("Not yet implemented")
    }

}