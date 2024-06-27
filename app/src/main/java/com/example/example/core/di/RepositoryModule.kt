package com.example.example.core.di

import com.example.example.core.data.repository.AppRepository
import com.example.example.core.data.repository.IAppRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val repositoryModule = module {
//    single { AppRepository(get(), get()) }
    single<IAppRepository> { AppRepository(get(), get()) }
}