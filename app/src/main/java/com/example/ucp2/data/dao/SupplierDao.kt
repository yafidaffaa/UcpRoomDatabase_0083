package com.example.ucp2.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ucp2.data.entity.Supplier
import kotlinx.coroutines.flow.Flow

@Dao
interface SupplierDao {
    @Insert
    suspend fun insertSpl(supplier: Supplier)

    @Query("SELECT * FROM supplier ORDER BY nama ASC")
    fun getAllSpl(): Flow<List<Supplier>>

    @Query("SELECT COUNT(*) > 0 FROM supplier WHERE id = :id")
    suspend fun isIdExists(id: String): Boolean

}