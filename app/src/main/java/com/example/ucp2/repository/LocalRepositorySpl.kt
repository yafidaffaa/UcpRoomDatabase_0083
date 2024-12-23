package com.example.ucp2.repository

import com.example.ucp2.data.dao.SupplierDao
import com.example.ucp2.data.entity.Supplier
import kotlinx.coroutines.flow.Flow

data class LocalRepositorySpl(
    private val supplierDao: SupplierDao
): RepositorySpl {
    override suspend fun insertSpl(supplier: Supplier) {
        supplierDao.insertSpl(supplier)
    }

    override fun getAllSpl(): Flow<List<Supplier>> {
        return supplierDao.getAllSpl()
    }

    override suspend fun isIdExists(id: String): Boolean {
        return supplierDao.isIdExists(id)
    }
}
