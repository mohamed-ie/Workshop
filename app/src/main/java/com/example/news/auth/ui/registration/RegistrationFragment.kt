package com.example.news.auth.ui.registration

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.news.MainActivity
import com.example.news.NewsApp
import com.example.news.R
import com.example.news.base.BaseFragment
import com.example.news.databinding.FragmentRegistrationBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


class RegistrationFragment : BaseFragment<FragmentRegistrationBinding, RegistrationViewModel>() {
    override val layoutRes: Int = R.layout.fragment_registration

    override val viewModelFactory: ViewModelProvider.Factory = object  :ViewModelProvider.Factory{
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            val app = requireActivity().application as NewsApp
            return  RegistrationViewModel(app.serviceLocator.authRepository) as T
        }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        handleUIEvent()
    }

    private fun handleUIEvent() {
        viewModel.uiEvent.onEach { event->
            when(event){
                is RegisterUIEvent.NavigateToLogin ->{
                    val email = event.email
                    //navigate to login with email
                    Toast.makeText(context, R.string.register_success, Toast.LENGTH_SHORT).show()
                }
            }
        }.launchIn(lifecycleScope)
    }
}