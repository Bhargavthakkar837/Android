package com.example.myappslauncher

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.launcher.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonLaunch = findViewById<Button>(R.id.buttonLaunch)
        buttonLaunch.setOnClickListener {
            val intent = Intent(this, LauncherActivity::class.java)
            startActivity(intent)
        }
    }
}
