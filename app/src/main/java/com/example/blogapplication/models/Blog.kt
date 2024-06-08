package com.example.blogapplication.models

import com.google.gson.annotations.SerializedName

class Blog(
    @SerializedName("id") var id: Int?= null,

    @SerializedName("link") var link: String?= null,
    @SerializedName("title") var title: Title? = Title(),

    @SerializedName("author") var author : Int?= null,



)


data class Title (

    @SerializedName("rendered" ) var rendered : String? = null

)


