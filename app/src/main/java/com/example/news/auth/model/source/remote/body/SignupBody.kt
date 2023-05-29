package com.example.news.auth.model.source.remote.body

data class SignupBody(
    val email: String,
    val password: String,
    val displayName: String,
    val returnSecureToken: Boolean = true
)

data class SignInBody(
    val email: String,
    val password: String,
    val returnSecureToken: Boolean = true
)