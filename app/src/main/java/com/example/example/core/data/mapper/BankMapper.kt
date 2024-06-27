package com.example.example.core.data.mapper

import com.example.example.core.data.source.local.entity.BankEntity
import com.example.example.core.data.source.remote.model.BankGeneral
import com.example.example.core.data.source.remote.model.BankUser
import com.inyongtisto.myhelper.extension.toModel

fun List<BankEntity>.toDomains(): List<BankUser> {
    return map { entity ->

//        val bankGeneral = BankGeneral(
//                id = entity.bank().id,
//                name = entity.bank().name,
//                image = entity.bank().image,
//                type = entity.bank().type,
//                alias = entity.bank().alias,
//                isSelected = entity.bank().isSelected,
//        )

        val bankGeneral = entity.bank().toModel(BankGeneral::class.java)

        BankUser(
                id = entity.id,
                account = entity.account,
                number = entity.number,
                bank = bankGeneral
        )
    }
}


fun List<BankUser>.toEntity(): List<BankEntity> {
    return map {
        val bankGeneral = it.bank.toModel(BankGeneral::class.java) ?: BankGeneral()
        BankEntity(
                id = it.id ?: 0,
                account = it.account,
                number = it.number,
                bank = listOf(bankGeneral)
        )
    }
}