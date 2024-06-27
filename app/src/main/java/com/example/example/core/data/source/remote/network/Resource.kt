package com.example.example.core.data.source.remote.network

import com.example.example.core.data.source.remote.response.Pagination
import com.example.example.util.Constants

data class Resource<out T>(
    val state: State, val body: T?, val message: String? = null,
    val errorCode: String? = null, val pagination: Pagination? = null) {

    companion object {

        fun <T> success(data: T?, pagination: Pagination? = null): Resource<T> {
            return Resource(State.SUCCESS, data, pagination = pagination)
        }

        fun <T> error(msg: String?, errorCode: String?): Resource<T> {
            return Resource(State.ERROR,
                    null,
                    msg ?: Constants.DEFAULT_ERROR,
                    errorCode ?: "ERROR"
            )
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(State.LOADING, data, null)
        }

    }
}