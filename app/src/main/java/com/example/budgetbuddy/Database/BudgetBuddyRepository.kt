package com.example.budgetbuddy.Database

import android.content.Context

class BudgetBuddyRepository(
    private val db:BudgetBuddyDB
) {
    suspend fun insert(budgetBuddy: BudgetBuddy) = db.getBudgetBuddyDao().insert(budgetBuddy)
    suspend fun delete(budgetBuddy: BudgetBuddy) = db.getBudgetBuddyDao().delete(budgetBuddy)

    suspend fun update(budgetBuddy: BudgetBuddy) = db.getBudgetBuddyDao().update(budgetBuddy)

    fun getAllBudgetBuddyItems(): List<BudgetBuddy> =
        db.getBudgetBuddyDao().getAllBudgetBuddyItems()
    companion object {
        @Volatile
        private var INSTANCE: BudgetBuddyRepository? = null

        fun getInstance(context: Context): BudgetBuddyRepository {
            synchronized(this) {
                return INSTANCE ?: BudgetBuddyRepository(BudgetBuddyDB.getInstance(context)).also {
                    INSTANCE = it
                }
            }
        }
    }
    }