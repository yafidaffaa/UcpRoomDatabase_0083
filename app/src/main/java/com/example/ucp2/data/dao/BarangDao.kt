package com.example.ucp2.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.ucp2.data.entity.Barang
import kotlinx.coroutines.flow.Flow

@Dao
interface BarangDao {
    @Insert
    suspend fun insertBrg(barang: Barang)

    @Delete
    suspend fun deleteBrg(barang: Barang)

    @Update
    suspend fun updateBrg(barang: Barang)

    @Query("SELECT * FROM barang ORDER BY harga ASC")
    fun getAllBrg(): Flow<List<Barang>>

    @Query("SELECT * FROM barang WHERE id = :id")
    fun getBrgById(id: String): Flow<Barang>

    @Query("SELECT COUNT(*) > 0 FROM barang WHERE id = :id")
    suspend fun isBrgIdExists(id: String): Boolean
}