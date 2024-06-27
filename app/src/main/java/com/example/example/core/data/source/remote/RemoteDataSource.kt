package com.example.example.core.data.source.remote

import com.example.example.core.data.source.remote.model.BankUser
import com.example.example.core.data.source.remote.network.ApiService
import com.example.example.core.data.source.remote.request.BankUserRequest

class RemoteDataSource(private val api: ApiService) {

    /**************** Bank User Start Here *********************/

    suspend fun getBankGeneral() = api.getBankGeneral()

    suspend fun getBankUser() = api.getBankUser()

    suspend fun deleteBankUser(mData: BankUser) = api.deleteBankUser(mData.id)

    suspend fun createBankUser(data: BankUserRequest) = api.createBankUser(data)

    suspend fun updateBankUser(data: BankUserRequest) = api.updateBankUser(data.id, data)

}