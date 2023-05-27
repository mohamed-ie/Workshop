package com.example.news.auth.ui.registration

import com.example.news.auth.model.repository.AuthRepository
import com.example.news.auth.model.source.local.UserData
import com.example.news.auth.model.source.local.model.SignupInfo
import com.example.news.helpers.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeAuthRepository(private val users: MutableList<UserData> = mutableListOf()) :
    AuthRepository {
    override suspend fun login(email: String, password: String) {
    }

    override fun signUp(
        email: String,
        password: String,
        displayName: String
    ): Flow<Resource<SignupInfo>> = flow {
        if (users.any { it.email == email })
            emit(Resource.Success(SignupInfo(error = "EMAIL EXIST", success = false)))
        else {
            emit(Resource.Success(SignupInfo(email = email, success = true)))
            users.add(UserData(email = email))
        }
    }

    override suspend fun saveUserData(userID: Int, name: String, email: String) {

    }

    override suspend fun logout() {

    }

    override suspend fun getUserData(): Flow<Int?> {
        TODO("Not yet implemented")
    }
}