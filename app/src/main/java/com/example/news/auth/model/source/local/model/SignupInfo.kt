package com.example.news.auth.model.source.local.model

import android.provider.ContactsContract.CommonDataKinds.Email

data class SignupInfo(
    val email: String? = null,
    val error: String? = null,
    val success: Boolean
)