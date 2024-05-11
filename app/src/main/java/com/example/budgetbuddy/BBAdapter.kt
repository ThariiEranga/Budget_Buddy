package com.example.budgetbuddy

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.budgetbuddy.Database.BudgetBuddy
import com.example.budgetbuddy.Database.BudgetBuddyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class BBAdapter(private var  items: MutableList<BudgetBuddy>, private val repository: BudgetBuddyRepository, private val viewModel: BudgetBuddyData) : Adapter<BBViewHolder>() {

    // Update data function
    fun updateData(newItems: List<BudgetBuddy>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }


    var context: Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BBViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.viewitem,parent,false)
        context = parent.context
        return BBViewHolder(view)
    }

    fun getItems(): List<BudgetBuddy> {
        return items
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: BBViewHolder, position: Int) {
        holder.categoryBB.text=items.get(position).Category
        holder.dateBB.text=items.get(position).Date
        holder.amountBB.text= "Rs. ${items[position].Amount}"

        holder.delBtn.setOnClickListener {
            GlobalScope.launch {
                repository.delete(items[position])
                // Refresh the data in the ViewModel
                val newData = repository.getAllBudgetBuddyItems()
                withContext(Dispatchers.Main) {
                    viewModel.setData(newData)
                    updateData(newData)
                }
            }

        }

        holder.upBtn.setOnClickListener {
            val intent = Intent(context, updateExpense::class.java).apply {
                putExtra("expenseId", items[position].id) // Pass expense ID
                putExtra("expenseDate", items[position].Date) // Pass expense date
                putExtra("expenseCategory", items[position].Category) // Pass expense category
                putExtra("expenseAmount", items[position].Amount) // Pass expense amount
            }
            context?.startActivity(intent)
        }
    }

}