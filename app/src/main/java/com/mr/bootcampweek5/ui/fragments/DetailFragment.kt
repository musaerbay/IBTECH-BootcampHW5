package com.mr.bootcampweek5.ui.fragments

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.mr.bootcampweek5.MainActivity
import com.mr.bootcampweek5.R
import com.mr.bootcampweek5.model.Result
import com.mr.bootcampweek5.util.Utils.IMAGE_END_POINT
import com.mr.bootcampweek5.viewmodel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment(R.layout.fragment_detail) {     //  easly fragment inflate

    private val args by navArgs<DetailFragmentArgs>()
    private lateinit var result:Result
    lateinit var viewModel: MovieViewModel
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel=(activity as MainActivity).viewModel

        result=args.now
        val image_backdrop=result.backdrop_path
        val image_poster=result.poster_path
        val title=result.original_title
        val overview=result.overview
        val releaseDate=result.release_date
        val voteAverege=result.vote_average
        val popularity=result.popularity


        Glide.with(this).load(IMAGE_END_POINT+image_backdrop).into(image_detail_backdrop)
        Glide.with(this).load(IMAGE_END_POINT+image_poster).into(image_detail_poster)
        text_details_popularity.text=popularity.toString()
        text_details_releasedate.text=releaseDate
        text_details_vote.text=voteAverege.toString()
        text_detail_title.text=title
        text_detail_overview.text=overview

        // Click fav button , add favori film
        btn_fab.setOnClickListener {
            viewModel.saveResult(result)
            Snackbar.make(view, "Movie saved successfully", Snackbar.LENGTH_SHORT).show()
            btn_fab.setImageResource(R.drawable.ic_baseline_star_rate_24)
        }
        // Click btn_back_detail  button , go PopularMovie screen
        btn_back_detail.setOnClickListener {
            val direction=DetailFragmentDirections.actionDetailFragmentToPopularMovieFragment()
            findNavController().navigate(direction)
        }

    }
}