package com.example.news.news.model.source.remote

import com.example.news.news.model.source.remote.dto.NewsDTO
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsWebservice {

    companion object {
        const val BASE_URL = "https://newsapi.org/v2/"
    }

    @GET("top-headlines")
    suspend fun getTopHeadlines(
        @Query("country") country: String,
    ): NewsDTO
}