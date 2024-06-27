package com.example.example.core.data.source.remote.response

data class BankUserResponse(
        val id: Int? = null,
        val account: String? = null,
        val number: String? = null,
        val bank: BankGeneralResponse? = null
)