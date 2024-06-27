package com.example.example.core.data.source.remote.network

import com.example.example.util.Constants.AUTH_TOKEN
import com.example.example.util.Constants.AUTH_TOKO
import com.example.example.util.Constants.BRAND
import com.example.example.util.Constants.TOKEN_TOKO
import com.example.example.util.Prefs
import com.inyongtisto.myhelper.extension.logs
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Interceptor to add auth token to requests
 */
class AuthInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        logs("intercept = token: $TOKEN_TOKO")
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
                .header(AUTH_TOKO, TOKEN_TOKO)
                .header(AUTH_TOKEN, Prefs.token)
                .header(BRAND, Prefs.brandId)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}