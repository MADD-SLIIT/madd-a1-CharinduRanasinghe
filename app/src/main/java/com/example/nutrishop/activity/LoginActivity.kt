package com.example.nutrishop.activity

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nutrishop.Model.UserModel
import com.example.nutrishop.databinding.ActivityLoginBinding
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.loginButton.setOnClickListener {
            loginUser()
        }

        binding.signUp.setOnClickListener {
            val intent = Intent(this, CreateAccountActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loginUser() {
        val email = binding.emailEditText.text.toString().trim()
        val password = binding.passwordEditText.text.toString().trim()

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Email and password cannot be empty", Toast.LENGTH_LONG).show()
            return
        }

        val ref = FirebaseDatabase.getInstance("https://nutrishop-c15c2-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Users")
        ref.orderByChild("email").equalTo(email).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    snapshot.children.forEach { child ->
                        val user = child.getValue(UserModel::class.java)
                        if (user?.password == password) {
                            Toast.makeText(this@LoginActivity, "Login successful", Toast.LENGTH_LONG).show()
                            navigateToIntroActivity()
                            return
                        }
                    }
                    Toast.makeText(this@LoginActivity, "Invalid password", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this@LoginActivity, "User not found", Toast.LENGTH_LONG).show()
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@LoginActivity, "Failed to read data: ${error.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun navigateToIntroActivity() {
        val intent = Intent(this, IntroActivity::class.java)
        startActivity(intent)
        finish()
    }
}


