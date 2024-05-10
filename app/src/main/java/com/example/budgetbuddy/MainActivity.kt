package com.example.budgetbuddy

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val AddBtn : Button = findViewById(R.id.AddBtn)
        val TotBtn : Button = findViewById(R.id.TotBtn)


        AddBtn.setOnClickListener{
            val intent = Intent(this, AddExpenses::class.java)
            startActivity(intent)
            finish()
        }

    }
}