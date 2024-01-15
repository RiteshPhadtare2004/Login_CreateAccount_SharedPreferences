package com.example.logincreateaccountapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class SignUpActivity : AppCompatActivity() {
    lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        val usernameEditText: EditText = findViewById(R.id.editTextUsernameSignUp)
        val passwordEditText: EditText = findViewById(R.id.editTextPasswordSignUp)
        val signUpButton: Button = findViewById(R.id.buttonSignUp)
        val loginTextView: TextView = findViewById(R.id.textViewLogin)

        signUpButton.setOnClickListener {
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (validateSignUp(username, password)) {
                // Save the new account details to SharedPreferences
                sharedPreferences.edit()
                    .putString("username", username)
                    .putString("password", password)
                    .apply()

                showToast("Account created successfully")
                finish() // Finish SignUpActivity and go back to LoginActivity
            } else {
                showToast("Invalid username or password")
            }
        }

        loginTextView.setOnClickListener {
            // Switch to LoginActivity
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

    private fun validateSignUp(username: String, password: String): Boolean {
        // You can replace this with your own validation logic
        return username.isNotEmpty() && password.isNotEmpty()
    }

    private fun showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}