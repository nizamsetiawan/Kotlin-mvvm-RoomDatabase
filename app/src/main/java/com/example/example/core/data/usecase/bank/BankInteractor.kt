package com.example.example.core.data.usecase.bank

import com.example.example.core.data.repository.IAppRepository
import com.example.example.core.data.source.remote.model.BankUser
import com.example.example.core.data.source.remote.network.Resource
import com.example.example.core.data.source.remote.request.BankUserRequest
import kotlinx.coroutines.flow.Flow

class BankInteractor(private val repo: IAppRepository) : BankUseCase {

    override fun getAll(): Flow<Resource<List<BankUser>>> = repo.getBankUser()

    override fun delete(data: BankUser): Flow<Resource<BankUser>> = repo.deleteBankUser(data)

    override fun create(data: BankUserRequest): Flow<Resource<BankUser>> = repo.createBankUser(data)

    override fun update(data: BankUserRequest): Flow<Resource<BankUser>> = repo.updateBankUser(data)

}