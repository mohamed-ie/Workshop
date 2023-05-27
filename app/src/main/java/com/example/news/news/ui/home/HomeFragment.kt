package com.example.news.news.ui.home


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.news.NewsApp
import com.example.news.R
import com.example.news.base.BaseFragment
import com.example.news.databinding.FragmentHomeBinding
import com.example.news.news.model.source.local.entitiy.Article
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HomeFragment : BaseFragment<FragmentHomeBinding,HomeViewModel>() {
    override val layoutRes: Int
        get() = R.layout.fragment_home
    override val viewModelFactory: ViewModelProvider.Factory
        get() = object  :ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val app = requireActivity().application as NewsApp
                return  HomeViewModel(app.serviceLocator.connectivityManager, app.serviceLocator.newsRepository) as T
            }
        }

    var list : List<Article> = listOf()
    var adapter = HomeNewsAdapter(list)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(!viewModel.isInternetAvailable()) {
            //true
            binding.recyclerView.visibility = View.GONE
            binding.searchBarNews.visibility = View.GONE
            binding.shimmerLayout.visibility = View.GONE
            binding.noConnection.visibility = View.VISIBLE
        }
        lifecycleScope.launch{
            viewModel.getNewsList().collect{
                list = it

                Log.i("TAG1", "onViewCreated: " + it.size)
            }
            withContext(Dispatchers.Main){
                if(list.size !=0){
                    adapter = HomeNewsAdapter(list)
                    binding.homeNewsAdapter = adapter
                    binding.shimmerLayout.visibility = View.GONE
                    binding.recyclerView.visibility = View.VISIBLE
                }

            }
        }

    }


}