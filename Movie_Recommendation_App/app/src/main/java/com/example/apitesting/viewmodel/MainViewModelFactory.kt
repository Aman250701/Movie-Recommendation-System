package com.example.apitesting.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.apitesting.repository.RecommendRepository

class MainViewModelFactory(private val recommendRepository: RecommendRepository) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(recommendRepository) as T
    }
}