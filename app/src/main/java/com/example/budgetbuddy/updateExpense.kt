package com.example.budgetbuddy

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.budgetbuddy.Database.BudgetBuddy
import com.example.budgetbuddy.Database.BudgetBuddyRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class updateExpense : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_update_expense)

        val date: EditText = findViewById(R.id.updatedate)
        val category: EditText = findViewById(R.id.updatecategory)
        val amount: EditText = findViewById(R.id.updateamount)
        val backBtn : Button = findViewById(R.id.backbtn)
        val updateBtn : Button = findViewById(R.id.UPBTN)

        val expenseId = intent.getIntExtra("expenseId", -1)
        val expenseDate = intent.getStringExtra("expenseDate")
        val expenseCategory = intent.getStringExtra("expenseCategory")
        val expenseAmount = intent.getStringExtra("expenseAmount")

        // Populate UI elements with existing expense details
        date.setText(expenseDate)
        category.setText(expenseCategory)
        amount.setText(expenseAmount)

        updateBtn.setOnClickListener {
            // Retrieve updated data from UI
            val updatedDate = date.text.toString()
            val updatedCategory = category.text.toString()
            val updatedAmount = amount.text.toString()

            // Create a new BudgetBuddy object with the updated data
            val updatedExpense = BudgetBuddy(
                id = expenseId,
                Date = updatedDate,
                Category = updatedCategory,
                Amount = updatedAmount
            )

            // Update the record in the database
            GlobalScope.launch {
                BudgetBuddyRepository.getInstance(applicationContext).update(updatedExpense)
            }

            // Navigate back to MainActivity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


        backBtn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}