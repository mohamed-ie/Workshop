package com.example.news.auth.model.source.remote.interceptor

import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor(private val apiKey:String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request()
            .newBuilder()

        val urlBuilder = chain.request()
            .url
            .newBuilder()
            .addQueryParameter("key",apiKey)

        requestBuilder.url(urlBuilder.build())

        return chain.proceed(requestBuilder.build())
    }
}