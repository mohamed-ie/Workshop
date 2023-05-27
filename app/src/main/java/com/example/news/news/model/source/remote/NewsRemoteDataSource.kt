package com.example.news.news.model.source.remote

import com.example.news.auth.model.source.remote.body.SignupBody
import com.example.news.auth.model.source.remote.dto.AuthDto
import com.example.news.news.model.source.remote.dto.NewsDTO
import kotlinx.coroutines.flow.Flow

interface NewsRemoteDataSource {
    suspend fun newsHeadLines(country:String): NewsDTO
}