package com.example.news.news.model.source.remote

import com.example.news.auth.model.source.remote.AuthWebservice
import com.example.news.news.model.source.remote.dto.NewsDTO
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class NewsRemoteDataSourceImpl(private val webservice: NewsWebservice
) : NewsRemoteDataSource {
    override suspend fun newsHeadLines(country:String): NewsDTO = webservice.getTopHeadlines(country)

}