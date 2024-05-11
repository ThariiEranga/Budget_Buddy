package com.example.budgetbuddy

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.budgetbuddy.Database.BudgetBuddy

class BudgetBuddyData: ViewModel() {

    private val _data = MutableLiveData<List<BudgetBuddy>>()
    val data: LiveData<List<BudgetBuddy>> = _data

    fun setData(data:List<BudgetBuddy>){
        _data.value = data
    }
}