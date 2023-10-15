package com.example.apitesting.application

import android.app.Application
import android.util.Log
import com.example.apitesting.api.RecommendApi
import com.example.apitesting.api.RetrofitHelper
import com.example.apitesting.repository.RecommendRepository

class MyApplication :
    Application() {
    //since manyView may have need of instance of repository class so we r defining a common repository class
    lateinit var recommendRepository: RecommendRepository
    override fun onCreate() {
        super.onCreate()
        initialize()
    }

    private fun initialize() {
        //Creating Instance of Retrofit i.e. connecting our interface and object -> it will return an interface to our api
        //instance of quoteAPI
        val recommendService = RetrofitHelper.getInstance().create(RecommendApi::class.java)
        //initializing instance of repository and instance of repository needs the instance of OurDataSource(API)
        recommendRepository = RecommendRepository(recommendService)


    }
}