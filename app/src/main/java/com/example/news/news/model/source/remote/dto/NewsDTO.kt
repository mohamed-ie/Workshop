package com.example.news.news.model.source.remote.dto

data class NewsDTO(

	val totalResults: Int,
    val articles: List<ArticleDTO>,
    val status: String
)