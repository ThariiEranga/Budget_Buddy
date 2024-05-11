package com.example.budgetbuddy

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.budgetbuddy.Database.BudgetBuddy
import com.example.budgetbuddy.Database.BudgetBuddyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.withContext


class BBAdapter(items:List<BudgetBuddy>, repository:BudgetBuddyRepository , viewModel:BudgetBuddyData): Adapter<BBViewHolder>() {

    var context: Context? = null
    val items = items
    val repository = repository
    val viewModel = viewModel

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BBViewHolder {
       val view = LayoutInflater.from(parent.context).inflate(R.layout.viewitem,parent,false)
        context = parent.context
        return BBViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: BBViewHolder, position: Int) {
        holder.categoryBB.text=items.get(position).Category
        holder.dateBB.text=items.get(position).Date
        holder.amountBB.text= items.get(position).Amount

//        holder.upBtn.setOnClickListener {
//           coroutineScope(Dispatchers.IO).launch{
//               repository.delete(items.get(position))
//               val data repository.getAllBBItems()
//               withContext(Dispatchers.Main){
//                   viewModel.setData(data)
//               }
//          }
//
//
//        }
        holder.delBtn.setOnClickListener {
            Toast.makeText(context,"delete",Toast.LENGTH_LONG).show()

        }


    }

}