package com.example.RadarPop.template.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.RadarPop.R
import com.example.RadarPop.template.Singleton
import com.example.RadarPop.template.api.BookDetailResp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class BookDetailFragment : Fragment() {

    private lateinit var TextViewName : TextView

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        TextViewName = view.findViewById(R.id.book_detail_name)
        callApi()

        view.findViewById<Button>(R.id.button_retour).setOnClickListener {
            findNavController().navigate(R.id.navigateToBookListFragment)
        }
        }

    private fun callApi() {
        val id = arguments?.getInt("bookId") ?: -1
        Singleton.bookApi.getBookDetail(id).enqueue(object : Callback<BookDetailResp> {
            override fun onFailure(call: Call<BookDetailResp>, t: Throwable) {
            }

            override fun onResponse(call: Call<BookDetailResp>, response: Response<BookDetailResp>) {
                if (response.isSuccessful && response.body() != null) {
                    TextViewName.text = response.body()!!.name
                }
            }
        })
    }
}