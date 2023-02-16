package com.example.testanas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.testanas.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.POST

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        val apiInterface = retrofit.create(ApiInterface::class.java)

        val call:Call<Post> = apiInterface.getPost()

        call.enqueue(object : Callback<Post?> {
            override fun onResponse(call: Call<Post?>, response: Response<Post?>) {
                binding.anas.text = response.body()!!.title
            }

            override fun onFailure(call: Call<Post?>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }
}