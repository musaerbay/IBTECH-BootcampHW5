package com.mr.bootcampweek5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import com.mr.bootcampweek5.db.MovieDatabase
import com.mr.bootcampweek5.repository.MovieRepository
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.mr.bootcampweek5.viewmodel.MovieViewModel
import com.mr.bootcampweek5.viewmodel.MovieViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var navController: NavController
    lateinit var viewModel: MovieViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.mr.bootcampweek5.R.layout.activity_main)

        val newsRepository = MovieRepository(MovieDatabase.invoke(this))
        val viewModelProviderFactory = MovieViewModelFactory(newsRepository)
        viewModel =
            ViewModelProvider(this, viewModelProviderFactory).get(MovieViewModel::class.java)
        val navHostFragment = supportFragmentManager.findFragmentById(com.mr.bootcampweek5.R.id.nav_host_fragment_container) as NavHostFragment?
        NavigationUI.setupWithNavController(bottomNavigationView, navHostFragment!!.navController)


    }

}