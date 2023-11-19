package com.example.myapplication.repos

import com.example.myapplication.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitModule {

    private lateinit var retrofitModule: Retrofit
    fun getRetrofitModule(): Retrofit {
        if (::retrofitModule.isInitialized) return retrofitModule
        retrofitModule = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
        return retrofitModule
    }

    fun getApi(): ApiInterface {
        return getRetrofitModule().create(ApiInterface::class.java)
    }
}