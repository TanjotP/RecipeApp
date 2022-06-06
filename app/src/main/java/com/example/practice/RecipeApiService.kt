package com.example.practice

import retrofit2.Call
import retrofit2.http.GET

interface RecipeApiService {
    @GET("/search?q=banana" + Api.API_ID + Api.API_KEY)
    fun listRecipes(): Call<RecipeApi>
}