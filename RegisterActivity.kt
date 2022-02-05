package com.example.hirollr

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        //Create two buttons used for account user submission and for loging into account activities
        val submitButton= findViewById<Button>(R.id.subButton)
        val loginButton2 = findViewById<Button>(R.id.logButton2)
        //Stores the values as strings for username, email, password, and confirming the password
        val username = findViewById<EditText>(R.id.username).text.toString()
        val email = findViewById<EditText>(R.id.email).text.toString()
        val password = findViewById<EditText>(R.id.password).text.toString()
        val cpassword = findViewById<EditText>(R.id.cpassword).text.toString()

        //If statements to make sure user enters a valid input for each text field
        if (username == "admin" && email == "user@hirollr.com")
        {
            if (password == "password" && cpassword == "password") {

                //Proceed to the dashboard activity once successfully signed on
                val intent = Intent(this, DashboardActivity::class.java)
                startActivity(intent)

                //Toast message allows the user that he or she have not enter valid input in the text
                //fields
            } else {
                Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show()
            }
        }
        //Submit button is used once the user is done registering and allows entrance to next activity
        submitButton.setOnClickListener{

            //start the activity
            val intent = Intent(this, DashboardActivity::class.java)
            startActivity(intent)
        }
        //Button used to sign on instead of register fom the register page
        loginButton2.setOnClickListener{

            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }


    }
}