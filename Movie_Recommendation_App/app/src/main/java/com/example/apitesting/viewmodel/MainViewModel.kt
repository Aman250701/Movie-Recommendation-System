package com.example.apitesting.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.apitesting.model.MovieData
import com.example.apitesting.model.RecommendedMovieData
import com.example.apitesting.repository.RecommendRepository
import retrofit2.Response

class MainViewModel(private val recommendRepository: RecommendRepository) : ViewModel() {
    val movieResponse: LiveData<Response<MovieData>>
        get() = recommendRepository.movieResponse
    val recommendedMovieResponse: LiveData<Response<RecommendedMovieData>>
        get() = recommendRepository.recommendedMovieResponse


    suspend fun getMovies() {
        recommendRepository.getMovieList()
    }

    suspend fun recommendMovie(movie_name: String) {
        recommendRepository.recommendMovie(movie_name)
    }
}