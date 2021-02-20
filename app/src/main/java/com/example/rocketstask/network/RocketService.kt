package com.example.rocketstask.network

import com.example.rocketstask.model.Rocket
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

private const val BASE_URL = "https://api.spacexdata.com/v3/"

private val retrofit = Retrofit.Builder()
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .addConverterFactory(GsonConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface RocketService {
    @GET("rockets")
   suspend fun getAll(): Response<List<Rocket>>

    @GET("rockets/{rocket_id}")
    suspend fun getRocketSelected(@Path("rocket_id")rocket_id:String): Response<Rocket>


}

object RocketApi {
    val retrofitService : RocketService by lazy {
        retrofit.create(RocketService::class.java)
    }
}