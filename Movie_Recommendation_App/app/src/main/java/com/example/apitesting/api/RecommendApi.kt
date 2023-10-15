package com.example.apitesting.api

import com.example.apitesting.model.MovieData
import com.example.apitesting.model.RecommendedMovieData
import retrofit2.Response
import retrofit2.http.*

interface RecommendApi {
    @GET("/")
    suspend fun getMovie(): Response<MovieData>

    @FormUrlEncoded
    @POST("/recommend")
    suspend fun getMovieRecommendation(@Field("movie_name")  movie_name: String): Response<RecommendedMovieData>
}