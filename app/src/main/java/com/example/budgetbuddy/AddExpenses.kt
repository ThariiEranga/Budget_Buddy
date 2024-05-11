package com.example.budgetbuddy

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddExpenses : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_expenses)

        val date: EditText = findViewById(R.id.editTextDate)
        val category: EditText = findViewById(R.id.editTextCategory)
        val amount: EditText = findViewById(R.id.editTextAmount)
        val backBtn : Button = findViewById(R.id.backbtn)
        val AddBtn : Button = findViewById(R.id.buttonAddExpense)

        backBtn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}