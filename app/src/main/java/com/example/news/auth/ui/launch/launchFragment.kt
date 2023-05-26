package com.example.news.auth.ui.launch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.news.R
import com.example.news.auth.model.repository.AuthRepository
import com.example.news.databinding.FragmentLaunchBinding
import com.example.news.di.ServiceLocator
import com.example.news.di.ServiceLocatorImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LaunchFragment : Fragment() {

    private lateinit var binding: FragmentLaunchBinding
    private lateinit var viewModel: LaunchViewModel
    private lateinit var factory: LaunchViewModelFactory
    private lateinit var authRepository: AuthRepository
    private lateinit var serviceLocator: ServiceLocator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        serviceLocator = ServiceLocatorImpl(requireContext())
        factory = LaunchViewModelFactory(serviceLocator.authRepository)

        viewModel = ViewModelProvider(this, factory).get(LaunchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_launch, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        lifecycleScope.launch(Dispatchers.Default) {
            if (!viewModel.checkIfUserLogged()) {
                //true
                navController.navigate(R.id.action_launchFragment_to_home_graph)
            } else {
                //false
                navController.navigate(R.id.action_launchFragment_to_auth_graph)
            }
        }

    }


}