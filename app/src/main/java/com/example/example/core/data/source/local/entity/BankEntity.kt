package com.example.example.core.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.example.core.data.source.local.converter.BankTypeConverter
import com.example.example.core.data.source.remote.model.BankGeneral

/**
 * Created by Tisto on 3/11/2021.
 */

@Entity
@TypeConverters(BankTypeConverter::class)
class BankEntity(
    @PrimaryKey
    val id: Int = 0,
    val account: String? = null,
    val number: String? = null,
    val bank: List<BankGeneral> = emptyList()
) {
    fun bank(): BankGeneral {
        return if (bank.isNotEmpty()) bank.first() else BankGeneral()
    }
}