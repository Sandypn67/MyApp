package com.example.RadarPop.template.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.RadarPop.R


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class BookListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var loader : ProgressBar
    private lateinit var error_gen : TextView

    private val adapter = BookAdapter(listOf(), ::onClickedBook)
    private val viewModel: BookListViewModel by viewModels()

    private val layoutManager = LinearLayoutManager(context)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.book_recyclerview)
        loader = view.findViewById(R.id.book_loader)
        error_gen = view.findViewById(R.id.book_error)
        recyclerView.apply {
            layoutManager = this@BookListFragment.layoutManager
            adapter =this@BookListFragment.adapter
        }
        viewModel.bookList.observe(viewLifecycleOwner, Observer { bookModel ->
            loader.isVisible = bookModel is BookLoader
            error_gen.isVisible = bookModel is BookError
            if(bookModel is BookSuccess) {
                adapter.updateList(bookModel.bookList)
            }

        })

    }

    private fun showList(bookList: List<Book>) {
        adapter.updateList(bookList)
    }
    private fun onClickedBook(id: Int) {
        findNavController().navigate(R.id.navigateToBookDetailFragment, bundleOf("bookId" to (id+1)))
    }
}