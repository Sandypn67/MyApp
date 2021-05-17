package com.example.RadarPop.template.api

data class BookDetailResp(
        val name: String,
        val types : List<BookSlot>
)

data class BookSlot(
        val slot: Int,
        val type: BookType
)

data class BookType(
        val name :String,
        val url : String
)