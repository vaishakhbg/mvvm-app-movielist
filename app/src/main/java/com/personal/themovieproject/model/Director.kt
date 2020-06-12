package com.personal.themovieproject.model

import com.google.gson.annotations.SerializedName

data class Director (

    @SerializedName("id")
    var id:String,

    @SerializedName("name")
    var name:String,

    @SerializedName("biography")
    var description:String,

    @SerializedName("profile_path")
    var img_path:String
)



