package com.example.example.core.data.source.local

import com.example.example.core.data.source.local.entity.BankEntity

class LocalDataSource(private val db: AppDatabase) {
    fun getBank() = db.daoBank().getAll()
    fun insertBank(body: List<BankEntity>) = db.daoBank().insert(body)
    fun dropAllBank() = db.daoBank().deleteAll()
    fun dropBank(data: BankEntity) = db.daoBank().delete(data)
}