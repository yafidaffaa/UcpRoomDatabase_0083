package com.example.ucp2.ui.viewmodel.supplier

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Supplier
import com.example.ucp2.repository.RepositorySpl
import kotlinx.coroutines.launch

class InsertSplViewModel(private val RepositorySpl: RepositorySpl) : ViewModel() {

    var uiState by mutableStateOf(SplUIState())

    fun updateState(supplierEvent: SupplierEvent) {
        uiState = uiState.copy(
            supplierEvent = supplierEvent,

            )
    }

    private fun validateFields(): Boolean {
        val event = uiState.supplierEvent

        val errorState = FormSplErrorState(
            id = if (event.id.isNotEmpty()) null else "ID tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            kontak = if (event.kontak.isNotEmpty()) null else "Kontak tidak boleh kosong",
            alamat = if (event.alamat.isNotEmpty()) null else "Alamat tidak boleh kosong",
        )

        uiState = uiState.copy(
            isEntryValid = errorState,
        )

        return errorState.isValid()

    }

    fun saveDataSpl(onSuccess: () -> Unit) {
        val currentEvent = uiState.supplierEvent
        if (validateFields()) {
            viewModelScope.launch {
                val isIdExists = RepositorySpl.isIdExists(currentEvent.id)

                if (isIdExists) {
                    uiState = uiState.copy(
                        snackbarMessage = "ID sudah digunakan"
                    )
                } else {
                    try {
                        RepositorySpl.insertSpl(currentEvent.toSupplierEntity())
                        uiState = uiState.copy(
                            snackbarMessage = "Data berhasil disimpan",
                            supplierEvent = SupplierEvent(),
                            isEntryValid = FormSplErrorState()
                        )
                        onSuccess()
                    } catch (e: Exception) {
                        uiState = uiState.copy(
                            snackbarMessage = "Data gagal disimpan"
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

    fun resetSplSnackBarMessage() {
        uiState = uiState.copy(snackbarMessage = null)
    }
}

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