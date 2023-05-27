package com.example.news.auth.ui.launch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.news.auth.model.repository.AuthRepository
import com.example.news.auth.model.source.local.UserDataStoreManager
import com.example.news.di.ServiceLocator
import java.lang.IllegalArgumentException

class LaunchViewModelFactory (private val authRepository: AuthRepository):
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(LaunchViewModel::class.java)) {
            LaunchViewModel(authRepository) as T
        } else {
            throw IllegalArgumentException("ViewModel Class not found")
        }
    }

}