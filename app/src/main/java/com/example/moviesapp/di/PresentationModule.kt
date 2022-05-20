package com.example.moviesapp.di


import com.example.moviesapp.ui.login.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val presentationModule = module {

    viewModel { LoginViewModel() }
    //viewModel { MovieListViewModel(get()) }
}