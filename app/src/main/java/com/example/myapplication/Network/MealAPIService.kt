package com.example.myapplication

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.scalars.ScalarsConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1/"
private val retrofit = Retrofit.Builder()
    .addConverterFactory(ScalarsConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()
interface MealAPIService {
    @GET("random.php")
    fun getrandom(): Call<String>

    @GET("search.php")
    fun getbyname(@Query("s") a: String): Call<String>
}
object MealAPI {
    val retrofitService : MealAPIService by lazy {
        retrofit.create(MealAPIService::class.java) }
}