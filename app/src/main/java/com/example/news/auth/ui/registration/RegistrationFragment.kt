package com.example.news.auth.ui.registration

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import com.example.news.R


class RegistrationFragment : Fragment() {

    private lateinit var binding:ViewDataBinding
    private lateinit var viewModel:RegistrationViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_registration,container,false)
        binding.lifecycleOwner = this
        return binding.root
    }

}