package com.example.ucp2.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ucp2.data.dao.BarangDao
import com.example.ucp2.data.dao.SupplierDao
import com.example.ucp2.data.entity.Barang
import com.example.ucp2.data.entity.Supplier

@Database(entities = [Barang::class, Supplier::class], version = 1, exportSchema = false)
abstract class DatabaseToko : RoomDatabase() {

    abstract fun barangDao(): BarangDao
    abstract fun supplierDao(): SupplierDao

    companion object {
        @Volatile
        private var Instance: DatabaseToko? = null

        fun getDatabase(context: Context): DatabaseToko {
            return (Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    DatabaseToko::class.java,
                    "DatabaseToko"
                )
                    .fallbackToDestructiveMigration()
                    .build().also { Instance = it }
            })
        }
    }
}
