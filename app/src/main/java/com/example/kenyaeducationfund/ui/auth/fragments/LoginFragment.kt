package com.example.kenyaeducationfund.ui.auth.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kenyaeducationfund.R
import com.example.kenyaeducationfund.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint


class LoginFragment: Fragment(R.layout.fragment_login) {
        private lateinit var binding: FragmentLoginBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)

        binding.tvRegisterNewAccount.setOnClickListener{
            if (findNavController().previousBackStackEntry != null){
                findNavController().popBackStack()
            } else
        findNavController().navigate(
            LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
        )

        }
    }
}