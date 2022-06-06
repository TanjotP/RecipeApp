package com.example.practice

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

import com.example.practice.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recipesListRequest(setupRetroService().listRecipes())
    }

    private fun setupRetroService(): RecipeApiService {
        val retro = Retrofit.Builder()
            .baseUrl(Api.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

        return retro.create(RecipeApiService::class.java)
    }

    private fun recipesListRequest(listRecipes: Call<RecipeApi>) {
        listRecipes.enqueue(object: Callback<RecipeApi> {
            override fun onResponse(call: Call<RecipeApi>, response: Response<RecipeApi>) {
                Log.d(MainActivity::class.simpleName, response.body().toString())

//                response.body()!!.hits.forEach { recipes ->
//                    Log.d(MainActivity::class.simpleName, recipes.label)
//                }
            }

            override fun onFailure(call: Call<RecipeApi>, t: Throwable) {
                Log.d(MainActivity::class.simpleName, "on FAILURE!!!!")
            }
        })
    }
}