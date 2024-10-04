package com.example.nutrishop.activity

import android.content.Intent
import android.os.Bundle
import android.text.method.PasswordTransformationMethod
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.nutrishop.Model.UserModel
import com.example.nutrishop.databinding.ActivityCreateAccountBinding
import com.google.firebase.database.FirebaseDatabase

class CreateAccountActivity : BaseActivity() {
    private lateinit var binding: ActivityCreateAccountBinding

    // Use the global reference to Firebase
    private val ref = FirebaseDatabase.getInstance("https://nutrishop-c15c2-default-rtdb.asia-southeast1.firebasedatabase.app").getReference("Users")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.signupButton.setOnClickListener {
            registerUser()
        }

        binding.togglePasswordVisibility.setOnClickListener {
            togglePasswordVisibility()
        }
    }

    private fun registerUser() {
        val email = binding.emailInput.text.toString().trim()
        val mobile = binding.mobileInput.text.toString().trim()
        val password = binding.passwordInput.text.toString().trim()

        if (email.isNotEmpty() && mobile.isNotEmpty() && password.isNotEmpty()) {
            val userId = ref.push().key
            val user = UserModel(email, mobile, password)

            userId?.let {
                ref.child(it).setValue(user).addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Toast.makeText(this, "User registered successfully", Toast.LENGTH_LONG).show()
                        navigateToLogin()
                    } else {
                        Toast.makeText(this, "Failed to register user", Toast.LENGTH_LONG).show()
                    }
                }
            }
        } else {
            Toast.makeText(this, "Please fill all the fields", Toast.LENGTH_LONG).show()
        }
    }

    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun togglePasswordVisibility() {

        if (binding.passwordInput.transformationMethod == null) {
            binding.passwordInput.transformationMethod = PasswordTransformationMethod.getInstance()
        } else {
            binding.passwordInput.transformationMethod = null
        }
    }
}
