package com.example.kenyaeducationfund.ui.auth.fragments

import android.os.Bundle

import androidx.navigation.fragment.findNavController
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.kenyaeducationfund.R
import com.example.kenyaeducationfund.databinding.FragmentRegisterBinding
import com.example.kenyaeducationfund.other.EventObserver
import com.example.kenyaeducationfund.ui.auth.AuthViewModel
import com.example.kenyaeducationfund.ui.snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_register.*

@AndroidEntryPoint
class RegisterFragment: Fragment(R.layout.fragment_register) {
    private lateinit var binding: FragmentRegisterBinding
    private lateinit var viewModel: AuthViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentRegisterBinding.bind(view)
        viewModel = ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)
        subscribeToObservers()
        binding.btnRegister.setOnClickListener {
                  viewModel.register(
                          binding.etEmail.text.toString(),
                          binding.etUsername.text.toString(),
                          binding.etPassword.text.toString(),
                          binding.etRepeatPassword.text.toString()
                    )
        }
        binding.tvLogin.setOnClickListener {
            if(findNavController().previousBackStackEntry != null) {
                findNavController().popBackStack()
            } else findNavController().navigate(
            RegisterFragmentDirections.globalActionCreatePostFragment()
            )
        }
    }

    private fun subscribeToObservers() {
        viewModel.registerStatus.observe(viewLifecycleOwner, EventObserver(
            onError = {
                registerProgressBar.isVisible = false
                snackbar(it)
            },
            onLoading = { registerProgressBar.isVisible = true }
        ) {
            registerProgressBar.isVisible = false
            snackbar(getString(R.string.success_registration))
        })
    }
}































