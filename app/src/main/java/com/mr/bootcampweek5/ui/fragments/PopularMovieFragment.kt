package com.mr.bootcampweek5.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.mr.bootcampweek5.R
import com.mr.bootcampweek5.adapter.ViewPagerAdapter
import kotlinx.android.synthetic.main.fragment_popular_movie.*


class PopularMovieFragment : Fragment(R.layout.fragment_popular_movie) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewPager.adapter = ViewPagerAdapter(childFragmentManager)
        tabLayout.setupWithViewPager(viewPager)
    }

}

