package com.example.news.news.model.source.local

import com.example.news.news.model.source.local.entitiy.Article
import kotlinx.coroutines.flow.Flow

interface LocalNewsDataSource {
    suspend fun saveFavouriteNewArticle(article: Article)

    suspend fun deleteFavouriteNewArticle(article: Article)
    fun getCashedArticles(): Flow<List<Article>>
    fun getFavouriteArticles(): Flow<List<Article>>
}