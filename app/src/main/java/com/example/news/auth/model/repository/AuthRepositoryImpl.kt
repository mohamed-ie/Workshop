package com.example.news.auth.model.repository

import com.example.news.auth.model.source.local.AuthLocalDataSource
import com.example.news.auth.model.source.local.UserData
import com.example.news.auth.model.source.local.model.SignupInfo
import com.example.news.auth.model.source.remote.AuthRemoteDataSource
import com.example.news.auth.model.source.remote.AuthWebservice
import com.example.news.auth.model.source.remote.body.SignInBody
import com.example.news.auth.model.source.remote.body.SignupBody
import com.example.news.helpers.Resource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class AuthRepositoryImpl(
    private val remoteDataSource: AuthRemoteDataSource,
    private val authLocalDataSource: AuthLocalDataSource,
    private val defaultDispatcher: CoroutineDispatcher
) : AuthRepository {
    override fun login(email: String, password: String):Flow<Resource<SignupInfo>> {
        return remoteDataSource.login(SignInBody(email, password))
            .map { dto ->
                val info = SignupInfo(
                    email = dto.email,
                    error = dto.error?.message,
                    success = dto.email != null
                )
                Resource.Success(info) as Resource<SignupInfo>
            }
            .catch { emit(Resource.Error) }
            .flowOn(defaultDispatcher)
    }

    override fun signUp(
        email: String,
        password: String,
        displayName: String
    ): Flow<Resource<SignupInfo>> {
        val signupBody = SignupBody(email, password, displayName)
        return remoteDataSource.signup(signupBody)
            .map { dto ->
                val info = SignupInfo(
                    email = dto.email,
                    error = dto.error?.message,
                    success = dto.email != null
                )
                Resource.Success(info) as Resource<SignupInfo>
            }
            .catch { emit(Resource.Error) }
            .flowOn(defaultDispatcher)
    }
    override suspend fun saveUserData(userID: Int, name: String, email: String) {
        authLocalDataSource.saveUserData(UserData(userID, name, email))
    }
    override suspend fun logout() {
        authLocalDataSource.resetUserData()
    }

    override suspend fun getUserData(): Flow<Int?> {
        return authLocalDataSource.getUserDataStore()
    }
}