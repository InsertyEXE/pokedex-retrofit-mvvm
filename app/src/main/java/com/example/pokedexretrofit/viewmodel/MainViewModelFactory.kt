package com.example.pokedexretrofit.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pokedexretrofit.rest.MainRepository

class MainViewModelFactory(private val repository: MainRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(this.repository) as T
        }
        throw IllegalArgumentException("ViewModel Not Found")
    }

}