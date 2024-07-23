package com.example.calculator

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        handleIncomingData()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, CalculatorFragment())
                .commit()
        }
    }

    private fun handleIncomingData() {
        val data = intent.getStringExtra("data_key")
        if (data != null) {
            // Handle the received data here
            // For example, you might want to display a Toast or use the data in some way
            Toast.makeText(this, "Received data: $data", Toast.LENGTH_LONG).show()
        }
    }
}
