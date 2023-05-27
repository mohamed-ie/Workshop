package com.example.news.news.model.source.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.news.news.model.source.local.entitiy.Article
import kotlinx.coroutines.flow.Flow

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertArticle(article: Article)

    @Query("SELECT * FROM article WHERE isFavorite = 1")
    fun getFavorites(): Flow<List<Article>>

    @Query("SELECT * FROM article WHERE isCached = 1")
    fun getCached(): Flow<List<Article>>

    @Delete
    suspend fun deleteArticle(article: Article)

}