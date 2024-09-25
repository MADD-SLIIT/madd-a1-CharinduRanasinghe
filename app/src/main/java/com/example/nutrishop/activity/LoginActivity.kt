package com.example.nutrishop.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.nutrishop.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            // Validate credentials and then navigate to IntroActivity
            val intent = Intent(this@LoginActivity, IntroActivity::class.java)
            startActivity(intent)
            finish() // Close LoginActivity to prevent returning to it
        }
    }
}