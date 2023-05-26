package com.example.news.auth.ui.launch

import androidx.lifecycle.ViewModel
import com.example.news.auth.model.repository.AuthRepository
import com.example.news.auth.model.source.local.UserDataStoreManager
import kotlinx.coroutines.flow.collect

class LaunchViewModel(private val authRepository: AuthRepository ): ViewModel()  {

    suspend fun checkIfUserLogged() :Boolean{
        var checker: Boolean = false
        authRepository.getUserData().collect{
            if(it == -1){
                checker = true
            }
        }
        return checker
    }
}