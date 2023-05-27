package com.example.news.news.model.source.remote

import com.example.news.news.model.source.remote.dto.ArticleDTO
import com.example.news.news.model.source.remote.dto.NewsDTO

class FakeNewsRemoteDataSource:NewsRemoteDataSource {
    override suspend fun newsHeadLines(country: String): NewsDTO {
        return NewsDTO(0, mutableListOf<ArticleDTO>(),"anyThing")
    }
}