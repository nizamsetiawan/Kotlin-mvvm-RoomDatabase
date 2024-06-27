package com.example.example.core.data.source.local.converter

import androidx.room.TypeConverter
import com.example.example.core.data.source.remote.model.BankGeneral
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BankTypeConverter {

    @TypeConverter
    fun stringToBank(json: String?): List<BankGeneral>? {
        val gson = Gson()
        val type = object : TypeToken<List<BankGeneral?>?>() {}.type
        return gson.fromJson<List<BankGeneral>>(json, type)
    }

    @TypeConverter
    fun bankToString(list: List<BankGeneral?>?): String? {
        val gson = Gson()
        val type = object : TypeToken<List<BankGeneral?>?>() {}.type
        return gson.toJson(list, type)
    }

}