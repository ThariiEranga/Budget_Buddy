package com.example.budgetbuddy.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
@Database(entities = [BudgetBuddy::class], version = 1)
abstract class BudgetBuddyDB:RoomDatabase() {

    abstract fun getBudgetBuddyDao():BudgetBuddyDao

    companion object{
        @Volatile
        private var  INSTANCE: BudgetBuddyDB? = null

        fun getInstance(context: Context):BudgetBuddyDB{
            synchronized(this){
                return INSTANCE ?: Room.databaseBuilder(
                    context,
                    BudgetBuddyDB::class.java,
                    "BudgetBuddy_db"
                ).build()
            }
        }
    }

}