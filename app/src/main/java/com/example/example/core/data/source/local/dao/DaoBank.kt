package com.example.example.core.data.source.local.dao

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import com.example.example.core.data.source.local.entity.BankEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface DaoBank {

    @Insert(onConflict = REPLACE)
    fun insert(data: BankEntity)

    @Insert(onConflict = REPLACE)
    fun insert(data: List<BankEntity>)

    @Delete
    fun delete(data: BankEntity)

    @Update
    fun update(data: BankEntity)

    @Query("SELECT * from BankEntity ORDER BY id ASC")
    fun getAll(): Flow<List<BankEntity>>

    @Query("SELECT * FROM BankEntity WHERE id = :id LIMIT 1")
    fun getById(id: Int): BankEntity

    @Query("DELETE FROM BankEntity")
    fun deleteAll()
}