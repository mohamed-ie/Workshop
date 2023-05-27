package com.example.news.news.model.repository

import com.example.news.news.model.source.remote.dto.NewsDTO
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews(country:String):Flow<NewsDTO>
}