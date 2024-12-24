package com.example.ucp2.ui.viewmodel.barang

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Barang
import com.example.ucp2.repository.RepositoryBrg
import kotlinx.coroutines.launch

class InsertBrgViewModel(private val RepositoryBrg: RepositoryBrg) : ViewModel() {

    var uiState by mutableStateOf(BrgUIState())

    fun updateState(barangEvent: BarangEvent) {
        uiState = uiState.copy(
            barangEvent = barangEvent,

            )
    }

    private fun validateFields(): Boolean {
        val event = uiState.barangEvent
        val errorState = FormBrgErrorState(
            id = if (event.id.isNotEmpty()) null else "ID tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            deskripsi = if (event.deskripsi.isNotEmpty()) null else "Deskripsi tidak boleh kosong",
            harga = if (event.harga.isNotEmpty()) null else "Harga tidak boleh kosong",
            stok = if (event.stok.isNotEmpty()) null else "Stok tidak boleh kosong",
            nama_supplier = if (event.nama_supplier.isNotEmpty()) null else "Nama supplier tidak boleh kosong",
        )

        uiState = uiState.copy(
            isEntryValid = errorState,
        )

        return errorState.isValid()

    }

    fun saveDataBrg(onSuccess: () -> Unit) {
        val currentEvent = uiState.barangEvent
        if (validateFields()) {
            viewModelScope.launch {
                val isIdExists = RepositoryBrg.isBrgIdExists(currentEvent.id)

                if (isIdExists) {
                    uiState = uiState.copy(
                        snackbarMessage = "ID sudah digunakan"
                    )
                } else {
                    try {
                        RepositoryBrg.insertBrg(currentEvent.toBarangEntity())
                        uiState = uiState.copy(
                            snackbarMessage = "Data berhasil disimpan",
                            barangEvent = BarangEvent(),
                            isEntryValid = FormBrgErrorState()
                        )
                        onSuccess()
                    } catch (e: Exception) {
                        e.printStackTrace()
                        uiState = uiState.copy(
                            snackbarMessage = "Data gagal disimpan: ${e.message}"
                        )
                    }
                }
            }
        } else {
            uiState = uiState.copy(
                snackbarMessage = "Input tidak valid. Periksa kembali data anda."
            )

        }
    }

    fun resetBrgSnackBarMessage() {
        uiState = uiState.copy(snackbarMessage = null)
    }
}

data class BrgUIState(
    val barangEvent: BarangEvent = BarangEvent(),
    val isEntryValid: FormBrgErrorState = FormBrgErrorState(),
    val snackbarMessage: String? = null,
)

data class FormBrgErrorState(
    var id: String? = null,
    var nama: String? = null,
    var deskripsi: String? = null,
    var harga: String? = null,
    var stok: String? = null,
    var nama_supplier: String? = null,
) {
    fun isValid(): Boolean {
        return id == null && nama == null && deskripsi == null && harga == null && stok == null && nama_supplier == null
    }
}

fun BarangEvent.toBarangEntity(): Barang = Barang(
    id = id,
    nama = nama,
    deskripsi = deskripsi,
    harga = harga,
    stok = stok,
    nama_supplier = nama_supplier
)

data class BarangEvent(
    var id: String = "",
    var nama: String = "",
    var deskripsi: String = "",
    var harga: String = "",
    var stok: String = "",
    var nama_supplier: String = ""
)