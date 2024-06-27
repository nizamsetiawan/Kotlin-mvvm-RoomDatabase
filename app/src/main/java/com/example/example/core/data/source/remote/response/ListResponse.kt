package com.example.example.core.data.source.remote.response

data class ListResponse<T>(
        var message: String = "",
        var code: String = "",
        val current_page: Int?,
        val first_page_url: String?,
        val from: Int?,
        val last_page: Int?,
        val last_page_url: String?,
        val next_page_url: String?,
        val path: String?,
        val per_page: Int?,
        val prev_page_url: String?,
        val to: Int?,
        val total: Int?,
        var data: List<T> = arrayListOf()
)