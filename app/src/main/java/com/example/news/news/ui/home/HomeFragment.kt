package com.example.news.news.ui.home


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.news.NewsApp
import com.example.news.R
import com.example.news.auth.ui.registration.RegistrationViewModel
import com.example.news.base.BaseFragment
import com.example.news.databinding.FragmentHomeBinding


class HomeFragment : BaseFragment<FragmentHomeBinding,HomeViewModel>() {
    override val layoutRes: Int
        get() = R.layout.fragment_home
    override val viewModelFactory: ViewModelProvider.Factory
        get() = object  :ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val app = requireActivity().application as NewsApp
                return  HomeViewModel() as T
            }
        }


}