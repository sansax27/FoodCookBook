package com.example.myapplication.Data

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.MealAPI
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback

class MealListViewModel(val name: String, val context: Application): ViewModel() {
    private val meals: MutableLiveData<List<MealDetail>> by lazy {
        MutableLiveData<List<MealDetail>>().also {
            loadMeals()
        }
    }

    fun getMeals(): LiveData<List<MealDetail>> {
        return meals
    }
    private fun loadMeals() {
        MealAPI.retrofitService.getbyname(name).enqueue(object : Callback,
            retrofit2.Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                try {
                    val d = response.body()!!
                    var json = JSONObject(d)
                    var mealsjson = json.getJSONArray("meals")
                    val mealslist = mutableListOf<MealDetail>()
                    val g = mealsjson.length()
                    for (i in 0 until g) {
                        val dummy = mealsjson.getJSONObject(i)
                        mealslist.add(
                            MealDetail(
                                dummy.getString("idMeal").toInt(),
                                dummy.getString("strMeal"),
                                dummy.getString("strCategory"),
                                dummy.getString("strMealThumb"),
                                dummy.getString("strInstructions")
                            )
                        )
                    }
                    meals.postValue(mealslist)
                } catch (e: Exception) {
                    var m = MealDetail(0,"No Such Dishes","Error Occured","Error Occured","Don't Add this to Favourites. It's Just for Information")
                    meals.postValue(mutableListOf<MealDetail>(m))
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                var m = MealDetail(0,"NoInternet","NoInternet","NoInternet","NoInternet")
                meals.postValue(mutableListOf<MealDetail>(m))
            }
        })
    }
}