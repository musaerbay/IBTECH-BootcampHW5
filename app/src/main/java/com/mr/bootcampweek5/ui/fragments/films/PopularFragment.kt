package com.mr.bootcampweek5.ui.fragments.films

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.mr.bootcampweek5.MainActivity
import com.mr.bootcampweek5.R
import com.mr.bootcampweek5.adapter.MovieResponsAdapter
import com.mr.bootcampweek5.resources.Resource
import com.mr.bootcampweek5.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_popular.*


class PopularFragment : Fragment(R.layout.fragment_popular) {  //  easly fragment inflate

    lateinit var viewModel: MovieViewModel
    lateinit var movieAdapter: MovieResponsAdapter
    val TAG = "PopularMovie"
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()

        viewModel.popularMovies.observe(viewLifecycleOwner, Observer { response ->
            when(response) {
                is Resource.Success -> {
                    hideProgressBar()
                    response.data?.let { it ->
                        movieAdapter.differ.submitList(it.results)
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    response.message?.let { message ->
                        Log.e(TAG, "An error occured: $message")
                    }
                }
                is Resource.Loading -> {
                    showProgressBar()
                }
            }
        })
    }

    private fun hideProgressBar() {
        paginationProgressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        paginationProgressBar.visibility = View.VISIBLE
    }

    //  to spread RecyclerView items across the page
    private fun setupRecyclerView() {
        movieAdapter = MovieResponsAdapter()
        rv_popular.apply {
            adapter = movieAdapter
            layoutManager = GridLayoutManager(activity,2)
        }
    }
}