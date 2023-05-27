package com.example.news.news.ui.home

import android.net.ConnectivityManager
import androidx.lifecycle.ViewModel
import com.example.news.utils.InternetChecker

class HomeViewModel(private val connectivityManager:ConnectivityManager):ViewModel() {

    fun isInternetAvailable():Boolean{
        val internetChecker = InternetChecker(connectivityManager)
        return internetChecker.isInternetAvailable()
    }
}