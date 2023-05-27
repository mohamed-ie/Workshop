package com.example.news.news.model.repository

import com.example.news.news.model.source.remote.NewsRemoteDataSource
import com.example.news.news.model.source.remote.dto.NewsDTO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NewsRepositoryImpl(
    private val remoteDataSource: NewsRemoteDataSource
) : NewsRepository {
    override fun getNews(country:String): Flow<NewsDTO> = flow {
        emit(remoteDataSource.newsHeadLines(country))
    }




}