package com.example.myapplication.Data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Meals")
data class MealDetail(
    @PrimaryKey val id: Int,
    val name:String,
    val category:String,
    val image: String,
    val instructions: String
)
