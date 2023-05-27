package com.example.news.auth.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.news.NewsApp
import com.example.news.R
import com.example.news.auth.ui.launch.LaunchViewModel
import com.example.news.auth.ui.registration.RegisterUIEvent
import com.example.news.base.BaseFragment
import com.example.news.databinding.FragmentLoginBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class LoginFragment : BaseFragment<FragmentLoginBinding,LoginViewModel>(){
    override val layoutRes: Int
        get() = R.layout.fragment_login
    override val viewModelFactory: ViewModelProvider.Factory
        get()  = object  :ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            val app = requireActivity().application as NewsApp
            return  LoginViewModel(app.serviceLocator.authRepository) as T
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.registerButton.setOnClickListener {
            navController.navigate(R.id.action_loginFragment_to_registrationFragment)
        }
        handleUIEvent()
    }

    private fun handleUIEvent() {
        viewModel.uiEvent.onEach { event->
            when(event){
                is RegisterUIEvent.NavigateToLogin ->{
                    Toast.makeText(context, R.string.register_success, Toast.LENGTH_SHORT).show()
                }
            }
        }.launchIn(lifecycleScope)
    }



}