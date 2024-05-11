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
import com.example.budgetbuddy.Database.BudgetBuddyDB
import com.example.budgetbuddy.Database.BudgetBuddyRepository
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AddExpenses : AppCompatActivity() {
    private lateinit var budgetBuddyRepository: BudgetBuddyRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_expenses)

        val date: EditText = findViewById(R.id.editTextDate)
        val category: EditText = findViewById(R.id.editTextCategory)
        val amount: EditText = findViewById(R.id.editTextAmount)
        val backBtn : Button = findViewById(R.id.backbtn)
        val AddBtn : Button = findViewById(R.id.buttonAddExpense)

        val db = BudgetBuddyDB.getInstance(applicationContext)
        budgetBuddyRepository = BudgetBuddyRepository(db)

        backBtn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        AddBtn.setOnClickListener {
            val expenseDate = date.text.toString()
            val expenseCategory = category.text.toString()
            val expenseAmount = amount.text.toString()

            // Create BudgetBuddy object
            if (expenseDate.isNotEmpty() && expenseCategory.isNotEmpty() && expenseAmount.isNotEmpty()) {
                // Create BudgetBuddy object
                val budgetBuddy = BudgetBuddy(
                    Date = expenseDate,
                    Category = expenseCategory,
                    Amount = expenseAmount
                )

                // Insert the expense into the database using repository
                GlobalScope.launch {
                    budgetBuddyRepository.insert(budgetBuddy)
                }
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }
            else{
                AddBtn.isEnabled = false
            }
            AddBtn.isEnabled = true
        }
    }
}