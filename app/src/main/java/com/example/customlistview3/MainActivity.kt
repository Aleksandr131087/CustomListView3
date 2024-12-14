package com.example.customlistview3

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var MagazinName: TextView
    private lateinit var ButtonCreate: Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MagazinName = findViewById(R.id.MagazinNameID)
        ButtonCreate = findViewById(R.id.ButtonCreateID)

        ButtonCreate.setOnClickListener {
            val intent = Intent(this, ActivityTwo::class.java)
            startActivity(intent)
        }

    }
}