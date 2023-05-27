package com.example.news.auth.ui.registration

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.R
import com.example.news.auth.model.repository.AuthRepository
import com.example.news.auth.model.source.local.model.SignupInfo
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

class RegistrationViewModel(private val repository: AuthRepository) : ViewModel() {
    private val emailRegex = Regex(EMAIL_PATTERN)

    val displayName = MutableStateFlow("")
    private val _displayNameError = MutableStateFlow<Int?>(null)
    val displayNameError = _displayNameError.asStateFlow()


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

    fun register() {
        updateFieldsErrors()
        _remoteError.update { "" }
        if (isDataValid())
            signup()
    }

    private fun updateFieldsErrors() {
        updateDisplayNameError()
        updateEmailError()
        updatePasswordError()
    }

    private fun updateDisplayNameError() {
        _passwordError.update {
            if (password.value.isBlank())
                R.string.empty_error
            else
                null
        }
    }

    private fun updateEmailError() {
        _emailError.update {
            if (email.value.isBlank()) R.string.empty_error
            else if (!emailRegex.matches(email.value)) R.string.email_not_formed_error
            else null
        }
    }

    private fun updatePasswordError() {
        _displayNameError.update {
            if (email.value.isBlank())
                R.string.empty_error
            else
                null
        }
    }

    private fun isDataValid(): Boolean =
        displayNameError.value == null
                && emailError.value == null
                && passwordError.value == null

    private fun signup() {
        repository.signUp(
            email = email.value,
            password = password.value,
            displayName = displayName.value
        ).onEach { resource ->
            when (resource) {
                Resource.Error -> {
                    _remoteError.update { "Unexpected error occurred\nPlease try again later" }
                }

                is Resource.Success -> {
                    handleSignUpInfo(resource.data)
                }
            }
        }.launchIn(viewModelScope)
    }

    private fun handleSignUpInfo(data: SignupInfo) {
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