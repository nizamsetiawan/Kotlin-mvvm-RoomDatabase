package com.example.example.core.data.source.remote.network

import com.example.example.core.data.source.remote.model.BankUser
import com.example.example.core.data.source.remote.request.BankUserRequest
import com.example.example.core.data.source.remote.response.BankGeneralResponse
import com.example.example.core.data.source.remote.response.DataResponse
import com.example.example.core.data.source.remote.response.ListResponse
import com.example.example.core.data.source.remote.response.SliderResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ApiService {

    @GET("bank-general")
    suspend fun getBankGeneral(): Response<ListResponse<BankGeneralResponse>>

    @GET("bank-user")
    suspend fun getBankUser(): Response<ListResponse<BankUser>>

    @POST("bank-user")
    suspend fun createBankUser(
        @Body bankUser: BankUserRequest
    ): Response<DataResponse<BankUser>>

    @PUT("bank-user/{id}")
    suspend fun updateBankUser(
        @Path("id") id: Int? = null,
        @Body bankUser: BankUserRequest
    ): Response<DataResponse<BankUser>>

    @DELETE("bank-user/{id}")
    suspend fun deleteBankUser(
        @Path("id") id: Int? = null
    ): Response<DataResponse<BankUser>>
}