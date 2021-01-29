package com.example.kenyaeducationfund.ui.auth.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.kenyaeducationfund.R
import com.example.kenyaeducationfund.databinding.FragmentLoginBinding
import com.example.kenyaeducationfund.databinding.FragmentRegisterBinding
import dagger.hilt.android.AndroidEntryPoint


class RegisterFragment: Fragment(R.layout.fragment_register) {
    private lateinit var binding: FragmentRegisterBinding
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterBinding.bind(view)

        binding.tvLogin.setOnClickListener{
            if (findNavController().previousBackStackEntry != null){
                findNavController().popBackStack()
            } else
            findNavController().navigate(
            RegisterFragmentDirections.actionRegisterFragmentToLoginFragment()
            )
        }
    }
}