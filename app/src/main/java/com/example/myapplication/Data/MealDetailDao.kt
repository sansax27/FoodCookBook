package com.example.myapplication.Data

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MealDetailDao {
  @Query("SELECT * FROM Meals")
  fun getAll(): LiveData<List<MealDetail>>
  @Insert
  fun storefav(meal: MealDetail)
  @Delete
  fun delete(meal : MealDetail)
}