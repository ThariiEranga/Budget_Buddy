package com.example.budgetbuddy.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface BudgetBuddyDao {

    @Insert
    suspend fun insert(budgetbuddy:BudgetBuddy)

    @Delete
    suspend fun delete(budgetbuddy:BudgetBuddy)

    @Update
    suspend fun update(budgetBuddy: BudgetBuddy)

    @Query("SELECT * FROM BudgetBuddy")
    fun getAllBudgetBuddyItems():List<BudgetBuddy>

}