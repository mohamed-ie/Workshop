package com.example.news.auth.model.source.remote

import com.example.news.auth.model.source.remote.body.SignInBody
import com.example.news.auth.model.source.remote.body.SignupBody
import com.example.news.auth.model.source.remote.dto.AuthDto
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AuthRemoteDataSourceImpl(
    private val webservice: AuthWebservice,
    private val ioDispatcher: CoroutineDispatcher
) : AuthRemoteDataSource {
    override fun signup(
        signupBody: SignupBody
    ): Flow<AuthDto> = flow {
        emit(webservice.signup(signupBody))
    }.flowOn(ioDispatcher)

    override fun login(signInBody: SignInBody): Flow<AuthDto> = flow {
        emit(webservice.login(signInBody))
    }.flowOn(ioDispatcher)
}