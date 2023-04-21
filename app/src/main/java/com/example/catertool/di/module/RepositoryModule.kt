package com.example.catertool.di.module

import com.example.catertool.viewmodel.LoginViewModel
import com.example.catertool.viewmodel.repository.LoginRepository
import com.example.catertool.viewmodel.repository.RegisterUserRepository
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule= module {
    single { LoginRepository(get()) }
    single { RegisterUserRepository(get()) }
}