package com.example.news

import android.app.Application
import com.example.news.di.ServiceLocator
import com.example.news.di.ServiceLocatorImpl

class NewsApp : Application() {
    val serviceLocator :ServiceLocator by lazy { ServiceLocatorImpl(this) }
}