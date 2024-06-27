package com.example.example.core.data.usecase.bank

import com.example.example.core.data.source.remote.model.BankUser
import com.example.example.core.data.source.remote.network.Resource
import com.example.example.core.data.source.remote.request.BankUserRequest
import kotlinx.coroutines.flow.Flow

interface BankUseCase {

    fun getAll(): Flow<Resource<List<BankUser>>>

    fun delete(data: BankUser): Flow<Resource<BankUser>>

    fun create(data: BankUserRequest): Flow<Resource<BankUser>>

    fun update(data: BankUserRequest): Flow<Resource<BankUser>>
}