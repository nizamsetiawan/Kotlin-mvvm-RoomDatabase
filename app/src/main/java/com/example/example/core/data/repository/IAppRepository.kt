package com.example.example.core.data.repository

import com.example.example.core.data.source.remote.model.BankUser
import com.example.example.core.data.source.remote.network.Resource
import com.example.example.core.data.source.remote.request.BankUserRequest
import kotlinx.coroutines.flow.Flow
import java.io.File

interface IAppRepository {

    fun getBankUser(): Flow<Resource<List<BankUser>>>

    fun createBankUser(data: BankUserRequest): Flow<Resource<BankUser>>

    fun updateBankUser(data: BankUserRequest): Flow<Resource<BankUser>>

    fun deleteBankUser(data: BankUser): Flow<Resource<BankUser>>

}