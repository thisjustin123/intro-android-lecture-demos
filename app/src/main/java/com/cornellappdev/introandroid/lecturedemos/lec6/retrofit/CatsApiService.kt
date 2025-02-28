package com.cornellappdev.introandroid.lecturedemos.lec6.retrofit

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CatsApiService {

    @GET("cats")
    suspend fun getCats(
        // Do NOT expose your API key like this. This is just for demo.
        @Header("X-Api-Key") apiKey: String = "jZPKNNR02mY2ekeVxMUBmA==xZTwG2O6OYa4BuXA",
        @Query("name") name: String? = null,
    ): List<Cat>
}

data class Cat(
    val length: String,
    val origin: String,
    val imageLink: String,
    val familyFriendly: Int,
    val shedding: Int,
    val generalHealth: Int,
    val playfulness: Int,
    val childrenFriendly: Int,
    val grooming: Int,
    val intelligence: Int,
    val otherPetsFriendly: Int,
    val minWeight: Int,
    val maxWeight: Int,
    val minLifeExpectancy: Int,
    val maxLifeExpectancy: Int,
    val name: String
)
