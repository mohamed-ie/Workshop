package com.example.news.news.ui.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.news.news.model.repository.NewsRepository
import com.example.news.news.model.source.local.entitiy.Article
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class FavoriteViewModel(private val newsRepository: NewsRepository) : ViewModel() {
    val searchQuery: StateFlow<String> = MutableStateFlow("")

    private val allFavorites = newsRepository.getFavouriteNews()

    private val _favorites: MutableStateFlow<List<Article>> = MutableStateFlow(listOf())
    val favorites = _favorites.asStateFlow()

    init {
        allFavorites.onEach {
            _favorites.update { it }
        }.launchIn(viewModelScope)

        searchQuery.onEach {
            _favorites.update {
                allFavorites.first().filter { it.title.contains(searchQuery.value, true) }
            }
        }.launchIn(viewModelScope)
    }

    fun deleteArticle(article: Article) {
        viewModelScope.launch {
            newsRepository.deleteFavourite(article)
        }
    }

}