package com.cornellappdev.introandroid.lecturedemos.lec6.retrofit

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

// Note: This is more or less the exact same for every networking call.
//  You just need to chance the URL and can add some optional parameters if you need to.
//  Feel free to reference for the rest of the course!
@Singleton
class RetrofitInstance @Inject constructor() {

    private val okHttpClient = OkHttpClient.Builder()
        .build()

    val catsApiService: CatsApiService by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.api-ninjas.com/v1/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CatsApiService::class.java)
    }
}
