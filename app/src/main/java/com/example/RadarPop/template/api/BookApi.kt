package com.example.RadarPop.template.api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path


interface BookApi {
    @GET("pokemon")
    fun getBookList(): Call<BookListResp>

    @GET("pokemon/{id}")
    fun getBookDetail(@Path("id") id:Int): Call<BookDetailResp>
}