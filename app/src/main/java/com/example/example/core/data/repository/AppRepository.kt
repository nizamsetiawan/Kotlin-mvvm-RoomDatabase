package com.example.example.core.data.repository

import com.example.example.core.data.mapper.toDomains
import com.example.example.core.data.mapper.toEntity
import com.example.example.core.data.source.local.LocalDataSource
import com.example.example.core.data.source.remote.RemoteDataSource
import com.example.example.core.data.source.remote.model.BankUser
import com.example.example.core.data.source.remote.network.Resource
import com.example.example.core.data.source.remote.network.ResponseHandler
import com.example.example.core.data.source.remote.network.ResponseListHandler
import com.example.example.core.data.source.remote.request.BankUserRequest
import com.example.example.core.data.source.remote.response.DataResponse
import com.example.example.core.data.source.remote.response.ListResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import retrofit2.Response

class AppRepository(val local: LocalDataSource, val remote: RemoteDataSource) : IAppRepository {

    override fun getBankUser() = object : ResponseListHandler<List<BankUser>, ListResponse<BankUser>>() {
        override suspend fun createCall(): Response<ListResponse<BankUser>> {
            return remote.getBankUser()
        }

        override suspend fun resultCall(data: ListResponse<BankUser>): List<BankUser> {
            local.dropAllBank()
            local.insertBank(data.data.toEntity())
            return data.data
        }

        override fun loadFromDB(): Flow<List<BankUser>> {
            return local.getBank().map { it.toDomains() }
        }
    }.asFlow().flowOn(Dispatchers.IO)

    override fun createBankUser(data: BankUserRequest) = object : ResponseHandler<BankUser, DataResponse<BankUser>>() {
        override suspend fun createCall(): Response<DataResponse<BankUser>> {
            return remote.createBankUser(data)
        }

        override suspend fun resultCall(data: DataResponse<BankUser>): BankUser {
            return data.data ?: BankUser()
        }
    }.asFlow().flowOn(Dispatchers.IO)

    override fun updateBankUser(data: BankUserRequest) = object : ResponseHandler<BankUser, DataResponse<BankUser>>() {
        override suspend fun createCall(): Response<DataResponse<BankUser>> {
            return remote.updateBankUser(data)
        }

        override suspend fun resultCall(data: DataResponse<BankUser>): BankUser {
            return data.data ?: BankUser()
        }
    }.asFlow().flowOn(Dispatchers.IO)

    override fun deleteBankUser(data: BankUser) = object : ResponseHandler<BankUser, DataResponse<BankUser>>() {
        override suspend fun createCall(): Response<DataResponse<BankUser>> {
            return remote.deleteBankUser(data)
        }

        override suspend fun resultCall(data: DataResponse<BankUser>): BankUser {
//            local.dropBankUser(mData)
            return data.data ?: BankUser()
        }
    }.asFlow().flowOn(Dispatchers.IO)
}