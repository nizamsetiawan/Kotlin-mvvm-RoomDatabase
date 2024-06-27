package com.example.example.core.data.source.remote.request

data class BankUserRequest(
    val id: Int? = null,
    val bankId: Int = 1,
    val account: String?,
    val number: String?
)