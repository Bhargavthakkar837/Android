package com.example.myappslauncher

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.launcher.R

class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.launcher_activity)

        val buttonOpenCurrencyConverter = findViewById<Button>(R.id.buttonOpenCurrencyConverter)
        val buttonOpenCalculator = findViewById<Button>(R.id.buttonOpenCalculator)

        buttonOpenCurrencyConverter.setOnClickListener {
            val intent = Intent().apply {
                action = "com.example.currencyconverter.ACTION_OPEN"
                putExtra("data_key", "Data from Launcher")
            }
            startActivity(intent)
        }

        buttonOpenCalculator.setOnClickListener {
            val intent = Intent().apply {
                action = "com.example.calculator.ACTION_OPEN"
                putExtra("data_key", "Data from Launcher")
            }
            startActivity(intent)
        }
    }
}
