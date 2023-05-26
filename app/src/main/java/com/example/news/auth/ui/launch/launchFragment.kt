package com.example.news.auth.ui.launch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.news.R
import com.example.news.auth.model.repository.AuthRepository
import com.example.news.auth.model.repository.AuthRepositoryImpl
import com.example.news.auth.model.source.local.UserDataStoreManager
import com.example.news.auth.model.source.remote.AuthWebservice
import com.example.news.auth.ui.registration.RegistrationViewModel
import com.example.news.databinding.FragmentLaunchBinding
import com.example.news.di.ServiceLocator
import com.example.news.di.ServiceLocatorImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [launchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class LaunchFragment : Fragment() {

    private lateinit var binding: FragmentLaunchBinding
    private lateinit var viewModel: LaunchViewModel
    private lateinit var factory : LaunchViewModelFactory
    private lateinit var authRepository: AuthRepository
    private lateinit var serviceLocator: ServiceLocator
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        serviceLocator = ServiceLocatorImpl(requireContext())
        factory = LaunchViewModelFactory(serviceLocator.authRepository)

        viewModel = ViewModelProvider(this,factory).get(LaunchViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_launch,container,false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        lifecycleScope.launch(Dispatchers.Default){
            if(!viewModel.checkIfUserLogged()){
                //true
                navController.navigate(R.id.action_launchFragment_to_home_graph)
            }else{
                //false
                navController.navigate(R.id.action_launchFragment_to_auth_graph)
            }
        }

    }


}