package com.example.myapplication.Data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel


class FavouritesViewModel(val context: Application): ViewModel() {
    private val meals= MealDatabase.getDatabase(context).MealDetailDao().getAll()
    fun getmeals(): LiveData<List<MealDetail>> {
        return meals
    }

    }

