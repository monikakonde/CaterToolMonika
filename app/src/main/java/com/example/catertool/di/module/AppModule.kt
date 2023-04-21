package com.example.catertool.di.module

import com.example.catertool.network.ApiClient
import org.koin.dsl.module

val appModule= module {
    factory { ApiClient() }
}