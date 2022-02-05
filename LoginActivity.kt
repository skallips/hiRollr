package com.example.hirollr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        //Create two vals in order to use id attributes
        val loginButton= findViewById<Button>(R.id.logButton)
        val registerButton = findViewById<Button>(R.id.regButton)

        //Create listener for login button

        loginButton.setOnClickListener{
            val username = findViewById<EditText>(R.id.username).text.toString()
            val password = findViewById<EditText>(R.id.password).text.toString()
            //Here the text field takes user input to access the account associated with the app
            if(username == "admin" && password == "password") {
                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)
            } else {
                //Create a toast notifying the user the log in information is incorrect
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show()
            }
        }
        //Create a register button that will redirect to registration page
        registerButton.setOnClickListener{
            //Begin activity
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}