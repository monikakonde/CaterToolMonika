package com.example.catertool.di.module

import com.example.catertool.viewmodel.LoginViewModel
import com.example.catertool.viewmodel.RegisterUserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { LoginViewModel(get()) }
    viewModel { RegisterUserViewModel(get()) }
}