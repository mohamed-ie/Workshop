package com.example.news.news.model.source.local

import com.example.news.news.model.source.local.entitiy.Article
import com.example.news.news.model.source.remote.dto.NewsDTO


class LocalNewsDataSourceImpl(private val newsDao: NewsDao) : LocalNewsDataSource {

    override suspend fun saveFavouriteNewArticle(article: Article) = newsDao.insertArticle(article)
    override suspend fun deleteFavouriteNewArticle(article: Article) = newsDao.deleteArticle(article)
    override fun getCashedArticles() = newsDao.getCached()
    override fun getFavouriteArticles() = newsDao.getFavorites()

}