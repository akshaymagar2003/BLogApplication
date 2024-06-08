package com.example.blogapplication

import com.example.blogapplication.models.Blog
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("wp-json/wp/v2/posts")
    suspend fun getPosts(@Query("per_page") perPage: Int, @Query("page") page: Int): List<Blog>
}

val retrofit = Retrofit.Builder()
    .baseUrl("https://blog.vrid.in/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val apiService = retrofit.create(ApiService::class.java)