package com.example.example.core.di

import com.example.example.ui.bank.BankUserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { BankUserViewModel(get()) }
}