package com.mr.bootcampweek5.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.android.material.snackbar.Snackbar
import androidx.lifecycle.Observer
import com.mr.bootcampweek5.MainActivity
import com.mr.bootcampweek5.R
import com.mr.bootcampweek5.adapter.MovieResponsAdapter
import com.mr.bootcampweek5.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_saved.*


class SavedFragment : Fragment(R.layout.fragment_saved) {       //  easly fragment inflate
    lateinit var movieresponseAdapter: MovieResponsAdapter
    lateinit var viewModel: MovieViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=(activity as MainActivity).viewModel
        setupRecyclerView()

        val itemTouchHelperCallback = object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.UP or ItemTouchHelper.DOWN,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                val movie = movieresponseAdapter.differ.currentList[position]
                viewModel.deleteResult(movie)
                Snackbar.make(view, "Successfully deleted "+movie.original_title, Snackbar.LENGTH_LONG).apply {
                    setAction("Undo") {
                        viewModel.saveResult(movie)
                    }
                    show()
                }
            }
        }

        ItemTouchHelper(itemTouchHelperCallback).apply {
            attachToRecyclerView(rv_saved)
        }

        viewModel.getSaved().observe(viewLifecycleOwner, Observer { articles ->
            movieresponseAdapter.differ.submitList(articles)
        })

    }

    private fun setupRecyclerView() {
        movieresponseAdapter = MovieResponsAdapter()
        rv_saved.apply {
            adapter = movieresponseAdapter
            layoutManager = StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL
            )
        }
    }
}