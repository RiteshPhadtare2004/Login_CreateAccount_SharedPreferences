package com.example.logincreateaccountapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        // Check if the user is already logged in
        if (isLoggedIn()) {
            startActivity(Intent(this, Home::class.java))
            finish()
        }

        val usernameEditText: EditText = findViewById(R.id.editTextUsername)
        val passwordEditText: EditText = findViewById(R.id.editTextPassword)
        val loginButton: Button = findViewById(R.id.buttonLogin)
        val signUpTextView: TextView = findViewById(R.id.textViewSignUp)

        loginButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (validateLogin(username, password)) {
                // Save the logged-in status to SharedPreferences
                sharedPreferences.edit().putBoolean("isLoggedIn", true).apply()

                // Redirect to the main activity or dashboard
                showToast("Login Successful")
                startActivity(Intent(this, Home::class.java))
                finish()
            } else {
                showToast("Invalid username or password")
            }
        }

        signUpTextView.setOnClickListener {
            // Switch to SignUpActivity
            startActivity(Intent(this, SignUpActivity::class.java))
        }
    }

    private fun validateLogin(username: String, password: String): Boolean {
        // You can replace this with your own validation logic
        val storedUsername = sharedPreferences.getString("username", "")
        val storedPassword = sharedPreferences.getString("password", "")

        return username == storedUsername && password == storedPassword
    }

    private fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean("isLoggedIn", false)
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}
