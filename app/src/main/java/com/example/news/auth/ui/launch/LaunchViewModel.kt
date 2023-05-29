package com.example.news.auth.ui.launch

import androidx.lifecycle.ViewModel
import com.example.news.auth.model.repository.AuthRepository
import kotlinx.coroutines.flow.first

class LaunchViewModel(private val authRepository: AuthRepository) : ViewModel() {

    suspend fun checkIfUserLogged(): Boolean {
        if (authRepository.getUserData().first() == -1 && authRepository.getUserData().first() == null ) {
            return true
        }
        return false
    }
}