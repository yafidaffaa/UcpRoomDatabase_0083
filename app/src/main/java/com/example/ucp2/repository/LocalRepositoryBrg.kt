package com.example.ucp2.repository

import com.example.ucp2.data.dao.BarangDao
import com.example.ucp2.data.entity.Barang
import kotlinx.coroutines.flow.Flow

data class LocalRepositoryBrg(
    private val barangDao: BarangDao
): RepositoryBrg {

    override suspend fun insertBrg(barang: Barang) {
        barangDao.insertBrg(barang)
    }

    override suspend fun deleteBrg(barang: Barang) {
        barangDao.deleteBrg(barang)
    }

    override suspend fun updateBrg(barang: Barang) {
        barangDao.updateBrg(barang)
    }

    override fun getAllBrg(): Flow<List<Barang>> {
        return barangDao.getAllBrg()
    }

    override fun getBrgById(id: String): Flow<Barang> {
        return barangDao.getBrgById(id)
    }

    override suspend fun isBrgIdExists(id: String): Boolean {
        return barangDao.isBrgIdExists(id)
    }
}
