package com.personal.themovieproject.service

import com.personal.themovieproject.model.AllCredits
import com.personal.themovieproject.model.Director
import com.personal.themovieproject.model.Movie
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("/3/movie/{movieID}?")
    fun getMovie(@Path("movieID") movieID: String, @Query("api_key") apiToken: String): Call<Movie>

    @GET("/3/person/{personID}?")
    fun getPerson(@Path("personID") personID: String, @Query("api_key") apiToken: String): Call<Director>

    @GET("3/person/{personID}/combined_credits?")
    fun getCredits(@Path("personID") personID: String,@Query("api_key") apiToken: String):Call<AllCredits>

}