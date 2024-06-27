package com.example.example.util

import android.app.Application
import com.chibatching.kotpref.Kotpref
import com.example.example.core.di.appModule
import com.example.example.core.di.repositoryModule
import com.example.example.core.di.useCaseModule
import com.example.example.core.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        Kotpref.init(this)
        startKoin {
            androidContext(this@MyApp)
            modules(listOf(appModule, useCaseModule, viewModelModule, repositoryModule))
        }
    }
}