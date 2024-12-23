package com.example.ucp2.repository

import com.example.ucp2.data.entity.Supplier
import kotlinx.coroutines.flow.Flow

interface RepositorySpl {
    suspend fun insertSpl(supplier: Supplier)

    fun getAllSpl(): Flow<List<Supplier>>

    suspend fun isIdExists(id: String): Boolean

}