package com.example.example.core.di

import androidx.room.Room
import com.example.example.core.data.source.local.AppDatabase
import com.example.example.core.data.source.local.LocalDataSource
import com.example.example.core.data.source.remote.RemoteDataSource
import com.example.example.core.data.source.remote.network.ApiConfig
import com.example.example.util.Constants
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {

    single {
        Room.databaseBuilder(
                androidContext(),
                AppDatabase::class.java,
                Constants.DB_NAME
        ).allowMainThreadQueries().build()
    }

    single { ApiConfig.provideApiService }

    single { RemoteDataSource(get()) }

    single { LocalDataSource(get()) }

}