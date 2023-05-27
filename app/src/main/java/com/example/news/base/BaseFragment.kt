package com.example.news.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController

abstract class BaseFragment<B :ViewDataBinding,V : ViewModel> :Fragment() {
    protected lateinit var binding : B
    protected lateinit var viewModel:V
    protected lateinit var navController:NavController

    @get:LayoutRes
    protected abstract val layoutRes : Int
    protected abstract val viewModelFactory : ViewModelProvider.Factory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        viewModel = viewModels<ViewModel>(factoryProducer = {viewModelFactory}).value as V
        navController = findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater,layoutRes,container,false)
        return binding.root
    }
}