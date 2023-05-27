package com.example.news.news.model.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.news.news.model.source.local.entitiy.Article

@Database(
    entities = [Article::class],
    version = 1,
    exportSchema = false
)
abstract class NewsDatabase : RoomDatabase() {
    companion object{
        const val DATABASE_NAME = "NEWS_DB"
    }

    abstract val dao: NewsDao
}