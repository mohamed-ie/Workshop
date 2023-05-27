package com.example.news.news.ui.home

import android.net.ConnectivityManager
import androidx.lifecycle.ViewModel
import com.example.news.news.model.repository.NewsRepository
import com.example.news.news.model.source.local.entitiy.Article
import com.example.news.utils.InternetChecker
import kotlinx.coroutines.flow.Flow

class HomeViewModel(private val connectivityManager:ConnectivityManager, private val newsRepository: NewsRepository):ViewModel() {

    fun isInternetAvailable():Boolean{
        val internetChecker = InternetChecker(connectivityManager)
        return internetChecker.isInternetAvailable()
    }

    fun getNewsList(): Flow<List<Article>>{
        return newsRepository.getNews("us")
    }
}