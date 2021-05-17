package com.example.RadarPop.template.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.RadarPop.template.Singleton
import com.example.RadarPop.template.api.BookListResp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookListViewModel: ViewModel() {
    val bookList : MutableLiveData<BookModel> = MutableLiveData()

    init {
        callApi()
    }

    private fun callApi() {
        bookList.value = BookLoader

        Singleton.bookApi.getBookList().enqueue(object : Callback<BookListResp> {
            override fun onFailure(call: Call<BookListResp>, t: Throwable) {
                bookList.value = BookError
            }

            override fun onResponse(call: Call<BookListResp>, response: Response<BookListResp>) {
                if (response.isSuccessful && response.body() != null) {
                    val bookResponse = response.body()!!
                    bookList.value = BookSuccess(bookResponse.results)
                }
                else {
                    bookList.value = BookError
                }
            }
        })
    }

}