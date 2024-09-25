package com.example.nutrishop.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nutrishop.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.googleButton.setOnClickListener {
            // Handle Google Sign-In
        }

        binding.createAccountButton.setOnClickListener {
            // Navigate to the create account screen
            startActivity(Intent(this@WelcomeActivity, CreateAccountActivity::class.java))
        }

        binding.loginLink.setOnClickListener {
            // Navigate to the login page
            startActivity(Intent(this@WelcomeActivity, LoginActivity::class.java))
        }
    }
}