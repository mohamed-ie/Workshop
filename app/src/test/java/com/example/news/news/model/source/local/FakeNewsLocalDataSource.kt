package com.example.news.news.model.source.local

import com.example.news.news.model.source.local.entitiy.Article
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class FakeNewsLocalDataSource(
    private val articleList:MutableList<Article>
):LocalNewsDataSource {
    override suspend fun saveFavouriteNewArticle(article: Article) {
        articleList.add(article)
    }

    override suspend fun deleteFavouriteNewArticle(article: Article) {
        articleList.remove(article)
    }

    override fun getCashedArticles(): Flow<List<Article>> =
        flow {
            emit(articleList.filter { article -> article.isCached })
        }

    override fun getFavouriteArticles(): Flow<List<Article>> = flow {
        emit(articleList.filter { article ->  article.isFavorite})
    }
}