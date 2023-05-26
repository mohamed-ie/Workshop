package com.example.news.auth.model.source.remote


class FakeAuthWebservice :AuthWebservice{
    override suspend fun login(email: String, password: String) {
        TODO("Not yet implemented")
    }

    override suspend fun signup(email: String, password: String, displayName: String) {
        TODO("Not yet implemented")
    }

}