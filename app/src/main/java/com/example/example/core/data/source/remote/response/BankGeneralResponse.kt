package com.example.example.core.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

data class BankGeneralResponse(
        val id: Int? = null,
        val name: String? = null,
        val image: String? = null,
        val type: String? = null,
        val alias: String? = null,
        var isSelected: Boolean = false
)