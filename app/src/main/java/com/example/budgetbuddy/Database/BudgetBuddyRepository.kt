package com.example.budgetbuddy.Database

class BudgetBuddyRepository(
    private val db:BudgetBuddyDB
) {
    suspend fun insert(budgetBuddy: BudgetBuddy) = db.getBudgetBuddyDao().insert(budgetBuddy)
    suspend fun delete(budgetBuddy: BudgetBuddy) = db.getBudgetBuddyDao().delete(budgetBuddy)

    fun getAllBudgetBuddyItems():List<BudgetBuddy> = db.getBudgetBuddyDao().getAllBudgetBuddyItems()
}