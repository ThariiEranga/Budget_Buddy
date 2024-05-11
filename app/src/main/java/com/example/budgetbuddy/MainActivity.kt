package com.example.budgetbuddy

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.budgetbuddy.Database.BudgetBuddyDB
import com.example.budgetbuddy.Database.BudgetBuddyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    private lateinit var budgetBuddyRepository: BudgetBuddyRepository
    private lateinit var adapter: BBAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val recyclerView: RecyclerView = findViewById(R.id.rvExpenses)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val db = BudgetBuddyDB.getInstance(applicationContext)
        budgetBuddyRepository = BudgetBuddyRepository(db)

        adapter = BBAdapter(mutableListOf(), budgetBuddyRepository, BudgetBuddyData())
        recyclerView.adapter = adapter

        // Update the adapter data when the activity is created
        GlobalScope.launch {
            val data = budgetBuddyRepository.getAllBudgetBuddyItems()
            withContext(Dispatchers.Main) {
                adapter.updateData(data)
            }
        }

        val AddBtn: Button = findViewById(R.id.AddBtn)
        AddBtn.setOnClickListener {
            val intent = Intent(this, AddExpenses::class.java)
            startActivity(intent)
            finish()
        }
        val totBtn:Button = findViewById(R.id.TotBtn)
        totBtn.setOnClickListener {
            showTotalExpensesDialog()
        }
    }
    private fun showTotalExpensesDialog() {
        val totalAmount = calculateTotalExpenses()
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setMessage("Total Expenses:Rs. $totalAmount")
            .setCancelable(true)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }

        val dialog = dialogBuilder.create()
        dialog.show()
    }

    private fun calculateTotalExpenses(): String {
        var total = 0.0
        val expenses = adapter.getItems() // Assuming you have a method to get all expenses from the adapter
        for (expense in expenses) {
            total += expense.Amount.toDouble()
        }
        return total.toString()
    }
}
