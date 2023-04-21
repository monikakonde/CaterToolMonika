package com.example.catertool.app

import android.app.Application
import com.example.catertool.Pref.PreferenceHelper
import com.example.catertool.di.module.appModule
import com.example.catertool.di.module.repositoryModule
import com.example.catertool.di.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application(){
    override fun onCreate() {
        super.onCreate()
        PreferenceHelper.init(this)
        startKoin {
            androidContext(this@App)
            modules(listOf(viewModelModule,repositoryModule,appModule))
        }
    }
}