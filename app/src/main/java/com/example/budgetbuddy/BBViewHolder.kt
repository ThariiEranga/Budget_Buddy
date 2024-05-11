package com.example.budgetbuddy

import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class BBViewHolder(view:View):ViewHolder(view) {

    val categoryBB: TextView = view.findViewById(R.id.textCategory)
    val dateBB:TextView = view.findViewById(R.id.textDate)
    val amountBB:TextView = view.findViewById(R.id.textAmount)
    val upBtn:ImageButton = view.findViewById(R.id.upBtn)
    val delBtn:ImageButton = view.findViewById(R.id.DelBtn)



}