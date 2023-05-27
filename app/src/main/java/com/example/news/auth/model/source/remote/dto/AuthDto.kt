package com.example.news.auth.model.source.remote.dto

data class AuthDto(
    val idToken: String? = null,
    val email: String? = null ,
    val refreshToken: String? = null,
    val expiresIn: String? = null,
    val displayName: String? = null,
    val error: AuthErrorDto? = null ,
    val registered: Boolean? = null
)
