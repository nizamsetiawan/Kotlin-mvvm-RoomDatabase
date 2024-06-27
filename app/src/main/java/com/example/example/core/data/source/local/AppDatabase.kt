package com.example.example.core.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.example.core.data.source.local.dao.*
import com.example.example.core.data.source.local.entity.*
import com.example.example.util.Constants

@Database(
        entities = [
            BankEntity::class,
        ], version = Constants.DB_VERSION, exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {
    abstract fun daoBank(): DaoBank
}