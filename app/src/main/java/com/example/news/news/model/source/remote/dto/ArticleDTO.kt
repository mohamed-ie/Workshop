package com.example.news.news.model.source.remote.dto


data class ArticleDTO(
    val publishedAt: String? = null,
    val author: String? = null,
    val urlToImage: String? = null,
    val description: String? = null,
    val source: SourceDTO,
    val title: String,
    val url: String,
    val content: String? = null
)
