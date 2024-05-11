package com.example.budgetbuddy.Database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BudgetBuddy(
    @PrimaryKey(autoGenerate = true)
    var id:Int?=null,
    val Date: String,
    val Category: String,
    val Amount: String
)
