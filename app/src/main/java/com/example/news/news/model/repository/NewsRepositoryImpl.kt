package com.example.news.news.model.repository

import com.example.news.news.model.source.local.LocalNewsDataSource
import com.example.news.news.model.source.local.entitiy.Article
import com.example.news.news.model.source.remote.NewsRemoteDataSource
import com.example.news.news.model.source.remote.dto.ArticleDTO
import com.example.news.news.model.source.remote.dto.NewsDTO
import com.example.news.utils.DATE_YEAR_MONTH_DAY_FORMAT_PATTERN
import com.example.news.utils.news.DateUtils
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class NewsRepositoryImpl(
    private val remoteDataSource: NewsRemoteDataSource,
    private val localNewsDataSource: LocalNewsDataSource
) : NewsRepository {
    override fun getNews(country:String): Flow<List<Article>> = flow {
        val remoteArticles = convert(remoteDataSource.newsHeadLines(country).articles,
            localNewsDataSource.getFavouriteArticles().first(),
            localNewsDataSource.getCashedArticles().first())
        remoteArticles.forEach {
            localNewsDataSource.saveFavouriteNewArticle(it)
        }
        emit(remoteArticles)
    }

    override suspend fun saveFavoriteNews(article: Article) {
        localNewsDataSource.saveFavouriteNewArticle(article)
    }

    override suspend fun deleteFavourite(article: Article) {
        localNewsDataSource.deleteFavouriteNewArticle(article)
    }

    override fun getFavouriteNews(): Flow<List<Article>> =
        localNewsDataSource.getFavouriteArticles()


    private fun convert(remoteArticles:List<ArticleDTO>,favouriteNews:List<Article>,cashedNews:List<Article>):List<Article> {
        val articleList = mutableListOf<Article>()
        remoteArticles.forEach {artical ->
            articleList.add(Article(artical.title,
                DateUtils.dateToHumanString(DATE_YEAR_MONTH_DAY_FORMAT_PATTERN,artical.publishedAt ?: ""),
                artical.source.name,
                artical.url,
                artical.urlToImage,
                artical.author,
            artical.content,
            artical.description,checkIsLocal(favouriteNews,artical.url),true))
        }
        return articleList
    }


    private fun checkIsLocal(favouriteNews:List<Article>,url:String):Boolean{
        if(favouriteNews.find { it.url == url } != null)
            return true
        return false
    }

}