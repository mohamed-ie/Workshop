package com.example.news.news.model.source.local.entitiy

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity("article")
data class Article(
    @PrimaryKey
    val title: String,
    @ColumnInfo("published_at")
    val publishedAt: String,
    val source: String,
    val url: String,
    @ColumnInfo("url_to_image")
    val urlToImage: String?,
    val author: String?,
    val content: String?,
    val description: String?,
    val isFavorite: Boolean,
    val isCached: Boolean
)