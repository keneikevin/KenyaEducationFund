package com.example.kenyaeducationfund.ui.auth.fragments

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.kenyaeducationfund.R
import com.example.kenyaeducationfund.databinding.FragmentLoginBinding
import com.example.kenyaeducationfund.other.EventObserver
import com.example.kenyaeducationfund.ui.Main.MainActivity
import com.example.kenyaeducationfund.ui.auth.AuthViewModel
import com.example.kenyaeducationfund.ui.snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment(R.layout.fragment_login) {
    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel:AuthViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentLoginBinding.bind(view)
        viewModel = ViewModelProvider(requireActivity()).get(AuthViewModel::class.java)
        binding.btnLogin.setOnClickListener {
            viewModel.login(
                    binding.etEmail.text.toString(),
                    binding.etPassword.text.toString()
            )
        }

        binding.tvRegisterNewAccount.setOnClickListener {
            if(findNavController().previousBackStackEntry != null) {
                findNavController().popBackStack()
            } else findNavController().navigate(
                    LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            )
        }
    }

    private fun subscribeToObservers() {
        viewModel.loginStatus.observe(viewLifecycleOwner, EventObserver(
                onError = {
                    binding.loginProgressBar.isVisible = false
                    snackbar(it)
                },
                onLoading = { binding.loginProgressBar.isVisible = true }
        ) {
            binding.loginProgressBar.isVisible = false
            Intent(requireContext(), MainActivity::class.java).also {
                startActivity(it)
                requireActivity().finish()
            }
        })
    }
}
