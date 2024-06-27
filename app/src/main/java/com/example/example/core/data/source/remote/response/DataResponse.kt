package com.example.example.core.data.source.remote.response

data class DataResponse<T>(
    var message: String = "",
    var code: String = "",
    var data: T? = null
)