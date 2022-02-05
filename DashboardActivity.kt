package com.example.hirollr

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        val venueButton1 = findViewById<Button>(R.id.venueActButton)
        venueButton1.setOnClickListener {
            val intent = Intent(this, VenueActivity::class.java)
            startActivity(intent)
        }
    }
}