package com.example.example.ui.bank

import androidx.lifecycle.*
import com.example.example.core.data.repository.AppRepository
import com.example.example.core.data.source.remote.model.BankUser
import com.example.example.core.data.source.remote.request.BankUserRequest
import com.example.example.core.data.usecase.bank.BankUseCase

class BankUserViewModel(private val usecase: BankUseCase) : ViewModel() {

//    val getLocal = usecase.getLocalBank().asLiveData()
//
//    fun getBankGeneral() = usecase.getBankGeneral().asLiveData()

    fun getAll() = usecase.getAll().asLiveData()

    fun delete(data: BankUser) = usecase.delete(data).asLiveData()

    fun create(data: BankUserRequest) = usecase.create(data).asLiveData()

    fun update(data: BankUserRequest) = usecase.update(data).asLiveData()

}