package com.example.myapplication.Data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities =[MealDetail::class], version =1)
abstract class MealDatabase: RoomDatabase() {
    abstract fun MealDetailDao(): MealDetailDao
    companion object {
        @Volatile
        private var instance: MealDatabase? = null

        fun getDatabase(context: Context) = instance
            ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    MealDatabase::class.java,
                    "movie_database"
                ).build().also { instance = it }
            }
    }
}