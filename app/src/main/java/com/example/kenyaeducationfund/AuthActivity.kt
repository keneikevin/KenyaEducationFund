package com.example.kenyaeducationfund

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kenyaeducationfund.databinding.ActivityAuthBinding
import com.example.kenyaeducationfund.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAuthBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}