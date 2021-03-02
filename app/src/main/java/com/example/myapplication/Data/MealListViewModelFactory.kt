package com.example.myapplication.Data

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MealListViewModelFactory(private val name: String, private val context: Application): ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MealListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MealListViewModel(name, context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}