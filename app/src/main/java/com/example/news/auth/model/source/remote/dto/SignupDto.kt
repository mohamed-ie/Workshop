package com.example.news.auth.model.source.remote.dto

data class SignupDto(
    val idToken: String?,
    val email: String?,
    val refreshToken: String?,
    val expiresIn: String?,
    val displayName: String?,
    val message: AuthErrorDto?
)
