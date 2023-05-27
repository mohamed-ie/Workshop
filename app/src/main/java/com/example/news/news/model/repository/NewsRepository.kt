package com.example.news.news.model.repository

import com.example.news.news.model.source.local.entitiy.Article
import com.example.news.news.model.source.remote.dto.NewsDTO
import kotlinx.coroutines.flow.Flow

interface NewsRepository {
    fun getNews(country:String):Flow<List<Article>>
    suspend fun saveFavoriteNews(article: Article)
    suspend fun deleteFavourite(article: Article)
    fun getFavouriteNews():Flow<List<Article>>
}