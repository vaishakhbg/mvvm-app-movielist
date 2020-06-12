package com.personal.themovieproject.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitRequest
{

    private val base_url = "https://api.themoviedb.org/"
    val tokenID = "692b0be204a494416a0c55ba2a8a529e"


    fun getRetrofitObject(): Retrofit {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(base_url)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit

    }



}