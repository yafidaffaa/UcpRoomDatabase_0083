package com.example.ucp2.ui.viewmodel.supplier

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Supplier
import com.example.ucp2.repository.RepositorySpl
import kotlinx.coroutines.launch



data class SplUIState(
    val supplierEvent: SupplierEvent = SupplierEvent(),
    val isEntryValid: FormSplErrorState = FormSplErrorState(),
    val snackbarMessage: String? = null
)

data class FormSplErrorState(
    var id: String? = null,
    var nama: String? = null,
    var kontak: String? = null,
    var alamat: String? = null,
) {
    fun isValid(): Boolean {
        return id == null && nama == null && kontak == null && alamat == null
    }
}

fun SupplierEvent.toSupplierEntity(): Supplier = Supplier(
    id = id,
    nama = nama,
    kontak = kontak,
    alamat = alamat,
)

data class SupplierEvent(
    var id: String = "",
    var nama: String = "",
    var kontak: String = "",
    var alamat: String = "",
)