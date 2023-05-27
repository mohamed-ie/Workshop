package com.example.news.auth.ui.launch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.news.NewsApp
import com.example.news.R
import com.example.news.auth.model.repository.AuthRepository
import com.example.news.auth.ui.registration.RegistrationViewModel
import com.example.news.base.BaseFragment
import com.example.news.databinding.FragmentLaunchBinding
import com.example.news.di.ServiceLocator
import com.example.news.di.ServiceLocatorImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LaunchFragment : BaseFragment<FragmentLaunchBinding,LaunchViewModel>(){
    override val layoutRes: Int
        get() = R.layout.fragment_launch
    override val viewModelFactory: ViewModelProvider.Factory
        get() = object  :ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val app = requireActivity().application as NewsApp
                return  LaunchViewModel(app.serviceLocator.authRepository) as T
            }
        }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            if (viewModel.checkIfUserLogged()) {
                //true
                //navController.navigate(R.id.action_launchFragment2_to_loginFragment2)
            } else {
                //false
                navController.navigate(R.id.action_launchFragment_to_loginFragment)
            }
        }

    }


}