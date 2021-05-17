package com.example.RadarPop.template

import com.example.RadarPop.template.BookApp.Companion.context
import com.example.RadarPop.template.api.BookApi
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

class Singleton {
    companion object{
        var cache = Cache(File(context?.cacheDir, "responses"), 10 * 1024 * 1024 ) //10 MiB

        val okHttpClient: OkHttpClient = OkHttpClient().newBuilder()
                .cache(cache)
                .build()

        val bookApi: BookApi = Retrofit.Builder()
                .baseUrl("https://pokeapi.co/api/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build()
                .create(BookApi::class.java)

    }
}



