package com.example.example.core.di

import com.example.example.core.data.usecase.bank.BankInteractor
import com.example.example.core.data.usecase.bank.BankUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single<BankUseCase> { BankInteractor(get()) }
}