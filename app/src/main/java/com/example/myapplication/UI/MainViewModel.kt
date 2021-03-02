package com.example.myapplication.UI

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.Data.MealDetail
import com.example.myapplication.MealAPI
import retrofit2.Call
import retrofit2.Response
import javax.security.auth.callback.Callback
import org.json.*
import java.lang.Exception

class MainViewModel: ViewModel() {
    private val meal : MutableLiveData<MealDetail> by lazy {
        MutableLiveData<MealDetail>().also {
            loadMeal()
        }
    }
    private fun loadMeal() {
        MealAPI.retrofitService.getrandom().enqueue(object: Callback,
            retrofit2.Callback<String> {
            override fun onResponse(call: Call<String>, response: Response<String>) {
                try {
                    var json = JSONObject(response.body())
                    var meals = json.getJSONArray("meals").getJSONObject(0)
                    var save: MealDetail = MealDetail(
                        meals.getString("idMeal").toInt(),
                        meals.getString("strMeal"),
                        meals.getString("strCategory"),
                        meals.getString("strMealThumb"),
                        meals.getString("strInstructions")
                    )
                    meal.setValue(save)
                } catch (e: Exception) {
                    meal.setValue(MealDetail(0,"Error Occured","Occured","Occured","Occured"))
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                meal.setValue(MealDetail(0,"NoInternet","NoInternet","0","NoInternet"))
            }
        })
    }
    fun getMeal(): LiveData<MealDetail> {
        return meal
    }
}








