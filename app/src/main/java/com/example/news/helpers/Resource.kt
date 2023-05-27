package com.example.news.helpers

sealed interface Resource<out T> {
    object Error: Resource<Nothing>
    class Success<T>(val data: T) : Resource<T>
}
