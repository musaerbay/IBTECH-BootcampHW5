package com.mr.bootcampweek5.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.bootcampweek5.model.MovieResponse
import com.mr.bootcampweek5.repository.MovieRepository
import com.mr.bootcampweek5.resources.Resource
import com.mr.bootcampweek5.util.Utils.API_KEY
import kotlinx.coroutines.launch
import retrofit2.Response
import com.mr.bootcampweek5.model.Result
import com.mr.bootcampweek5.util.Utils

class MovieViewModel(val repository: MovieRepository): ViewModel() {

    val popularMovies: MutableLiveData<Resource<MovieResponse>> = MutableLiveData()


    init {
        getPopularMovies(API_KEY)

    }

     fun getPopularMovies(apiKey:String)=viewModelScope.launch {
         popularMovies.postValue(Resource.Loading())
         val response=repository.getPopularMovie(apiKey, Utils.moviePage)
         popularMovies.postValue(handleMovies(response))
     }


    private fun handleMovies(response: Response<MovieResponse>):Resource<MovieResponse>{
        if(response.isSuccessful){
            response.body()?.let {
                return Resource.Success(it)
            }
        }
        return Resource.Error(response.message())
    }

    fun saveResult(result:Result) = viewModelScope.launch {
        repository.insert(result)
    }
    fun getSaved()=repository.getAllMovie()

    fun deleteResult(result:Result) = viewModelScope.launch {
        repository.delete(result)
    }

}