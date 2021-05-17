package com.example.RadarPop.template.api

import com.example.RadarPop.template.list.Book

data class BookListResp(
    val count :Int,
    val next: String,
    val results : List<Book>
)