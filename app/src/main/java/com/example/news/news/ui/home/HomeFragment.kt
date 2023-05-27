package com.example.news.news.ui.home


import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.news.NewsApp
import com.example.news.R
import com.example.news.base.BaseFragment
import com.example.news.databinding.FragmentHomeBinding


class HomeFragment : BaseFragment<FragmentHomeBinding,HomeViewModel>() {
    override val layoutRes: Int
        get() = R.layout.fragment_home
    override val viewModelFactory: ViewModelProvider.Factory
        get() = object  :ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val app = requireActivity().application as NewsApp
                return  HomeViewModel(app.serviceLocator.connectivityManager) as T
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(viewModel.isInternetAvailable()) {
            //true
            binding.recyclerView.visibility = View.GONE
            binding.searchBarNews.visibility = View.GONE
            binding.shimmerLayout.visibility = View.GONE
            binding.noConnection.visibility = View.VISIBLE
        }
    }


}