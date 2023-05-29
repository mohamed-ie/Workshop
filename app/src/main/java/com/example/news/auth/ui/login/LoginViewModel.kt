package com.example.news.auth.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.R
import com.example.news.auth.model.repository.AuthRepository
import com.example.news.auth.model.source.local.model.SignupInfo
import com.example.news.auth.ui.registration.RegisterUIEvent
import com.example.news.helpers.Resource
import com.example.news.utils.EMAIL_PATTERN
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class LoginViewModel(private val repository: AuthRepository): ViewModel(){
    private val emailRegex = Regex(EMAIL_PATTERN)

    val email = MutableStateFlow("")
    private val _emailError = MutableStateFlow<Int?>(null)
    val emailError = _emailError.asStateFlow()


    val password = MutableStateFlow("")
    private val _passwordError = MutableStateFlow<Int?>(null)
    val passwordError = _passwordError.asStateFlow()

    private val _remoteError = MutableStateFlow("")
    val remoteError = _remoteError.asStateFlow()

    private val _uiEvent = MutableSharedFlow<RegisterUIEvent>()
    val uiEvent = _uiEvent.asSharedFlow()


    fun login() {
        updateFieldsErrors()
        _remoteError.update { "" }
        if (isDataValid())
            signup()
    }

    private fun updateFieldsErrors() {
        updateEmailError()
        updatePasswordError()
    }


    private fun updateEmailError() {
        _emailError.update {
            if (email.value.isBlank()) R.string.empty_error
            else if (!emailRegex.matches(email.value)) R.string.email_not_formed_error
            else null
        }
    }

    private fun updatePasswordError() {
        _passwordError.update {
            if (email.value.isBlank())
                R.string.empty_error
            else
                null
        }
    }

    private fun isDataValid(): Boolean = emailError.value == null
                && passwordError.value == null

    private fun signup() {
        repository.login(
            email = email.value,
            password = password.value,
        ).onEach { resource ->
            when (resource) {
                Resource.Error -> {
                    _remoteError.update { "Unexpected error occurred\nPlease try again later" }
                }

                is Resource.Success -> {
                    handleLoginInfo(resource.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun handleLoginInfo(data: SignupInfo) {
        if (data.success)
            viewModelScope.launch {
                data.email?.let {
                    _uiEvent.emit(RegisterUIEvent.NavigateToLogin(data.email))
                }
            }
        else
            _remoteError.update {
                data.error ?: "Unexpected error occurred\nPlease try again later"
            }
    }
}