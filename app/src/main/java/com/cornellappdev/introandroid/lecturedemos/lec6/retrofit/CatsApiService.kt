package com.cornellappdev.introandroid.lecturedemos.lec6.retrofit

import com.google.gson.annotations.SerializedName
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

    // To exactly match JSON, we need to use `image_link` instead of `image`.
    @SerializedName("image_link")
    val imageLink: String,

    @SerializedName("family_friendly")
    val familyFriendly: Int,

    val shedding: Int,

    @SerializedName("general_health")
    val generalHealth: Int,

    val playfulness: Int,

    @SerializedName("children_friendly")
    val childrenFriendly: Int,

    val grooming: Int,
    val intelligence: Int,

    @SerializedName("other_pets_friendly")
    val otherPetsFriendly: Int,

    @SerializedName("min_weight")
    val minWeight: Int,

    @SerializedName("max_weight")
    val maxWeight: Int,

    @SerializedName("min_life_expectancy")
    val minLifeExpectancy: Int,

    @SerializedName("max_life_expectancy")
    val maxLifeExpectancy: Int,

    val name: String
)
