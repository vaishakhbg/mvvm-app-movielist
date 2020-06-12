package com.personal.themovieproject.model

import android.graphics.Bitmap
import android.media.Image
import com.google.gson.annotations.SerializedName

data class AllCredits (

    @SerializedName("id")
    var id:String,

    @SerializedName("crew")
    var crew:ArrayList<Project> = ArrayList<Project>()


)

data class Project(
    @SerializedName("id")
    var id:String,

    @SerializedName("title")
    var title: String,

    @SerializedName("release_date",alternate = ["first_air_date"])
    var date:String,

    @SerializedName("department")
    var department:String="",
    @SerializedName("popularity")
    var popularity:Double,
    @SerializedName("poster_path")
    var poster_path:String,
    @SerializedName("backdrop_path")
    var backdrop:String

)