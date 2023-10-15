package com.example.apitesting.repository

import android.graphics.Movie
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.apitesting.api.RecommendApi
import com.example.apitesting.model.MovieData
import com.example.apitesting.model.RecommendedMovieData
import retrofit2.Response

class RecommendRepository(private val recommendApi: RecommendApi) {
    //LiveData For API APIResponse
    private val responseLiveData = MutableLiveData<Response<MovieData>>()
    val movieResponse: LiveData<Response<MovieData>>
        get() = responseLiveData //defining getter property for quotes
    private val recommendedMovieResponseLiveData = MutableLiveData<Response<RecommendedMovieData>>()
    val recommendedMovieResponse: LiveData<Response<RecommendedMovieData>>
        get() = recommendedMovieResponseLiveData //defining getter property for quotes

    //getting Movies
    suspend fun getMovieList() {
        val apiResponse = recommendApi.getMovie()
        responseLiveData.postValue(apiResponse)

    }

    //gettingRecommended Movie
    suspend fun recommendMovie(movie_name: String) {
        //getting response from server
        val recommendedMovieResponse = recommendApi.getMovieRecommendation(movie_name)
        //posting into our live data
        recommendedMovieResponseLiveData.postValue(recommendedMovieResponse)


    }
}