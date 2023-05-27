package com.example.news.news.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.news.NewsApp
import com.example.news.R
import com.example.news.base.BaseFragment
import com.example.news.databinding.FragmentFavoriteBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class FavoriteFragment : BaseFragment<FragmentFavoriteBinding, FavoriteViewModel>() {
    override val layoutRes: Int = R.layout.fragment_favorite

    override val viewModelFactory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            val app = requireActivity().application as NewsApp
            return FavoriteViewModel(app.serviceLocator.newsRepository) as T
        }
    }

    private val favoriteAdapter: FavoriteAdapter = FavoriteAdapter(viewModel::deleteArticle)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel
        binding.recyclerView.adapter = favoriteAdapter
        listenToFavorites()
    }

    private fun listenToFavorites() {
        viewModel.favorites.onEach {
            favoriteAdapter.articles = it
        }.launchIn(lifecycleScope)
    }

}