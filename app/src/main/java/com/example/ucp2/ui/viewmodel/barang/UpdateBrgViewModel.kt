package com.example.ucp2.ui.viewmodel.barang

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Barang
import com.example.ucp2.repository.RepositoryBrg
import com.example.ucp2.ui.navigasi.DestinasiUpdateBrg
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class UpdateBrgViewModel(
    savedStateHandle: SavedStateHandle,
    private val RepositoryBrg: RepositoryBrg
): ViewModel(){
    var updateBrgUIState by mutableStateOf(BrgUIState())
        private set

    private val _id: String = checkNotNull(savedStateHandle[DestinasiUpdateBrg.ID])

    init {
        viewModelScope.launch {
            updateBrgUIState=RepositoryBrg.getBrgById(_id)
                .filterNotNull()
                .first()
                .toUIStateBrg()
        }
    }

    fun UpdateBrgState(barangEvent: BarangEvent){
        updateBrgUIState = updateBrgUIState.copy(
            barangEvent = barangEvent,
        )
    }

    fun ValidateFields(): Boolean{
        val event = updateBrgUIState.barangEvent
        val errorState = FormBrgErrorState(
            id = if (event.id.isNotEmpty()) null else "ID tidak boleh kosong",
            nama = if (event.nama.isNotEmpty()) null else "Nama tidak boleh kosong",
            deskripsi = if (event.deskripsi.isNotEmpty()) null else "Deskripsi tidak boleh kosong",
            harga = if (event.harga.isNotEmpty()) null else "Harga tidak boleh kosong",
            stok = if (event.stok.isNotEmpty()) null else "Stok tidak boleh kosong",
            nama_supplier = if (event.nama_supplier.isNotEmpty()) null else "Nama supplier tidak boleh kosong",
        )
        updateBrgUIState = updateBrgUIState.copy(isEntryValid = errorState)
        return errorState.isValid()
    }

    fun updateData(){
        val currentEvent = updateBrgUIState.barangEvent

        if (ValidateFields()){
            viewModelScope.launch {
                try{
                    RepositoryBrg.updateBrg(currentEvent.toBarangEntity())
                    updateBrgUIState = updateBrgUIState.copy(
                        snackbarMessage = "Data berhasil diupdate",
                        barangEvent = BarangEvent(),
                        isEntryValid = FormBrgErrorState()
                    )
                    println("snackBarMessage diatur: ${updateBrgUIState.snackbarMessage}")
                }catch (e: Exception){
                    updateBrgUIState = updateBrgUIState.copy(
                        snackbarMessage = "Data gagal diupdate"
                    )
                }
            }
        } else{
            updateBrgUIState = updateBrgUIState.copy(
                snackbarMessage = "Data gagal diupdate"
            )
        }
    }

    fun resetBrgSnackBarMessage(){
        updateBrgUIState = updateBrgUIState.copy(snackbarMessage = null)
    }
}

fun Barang.toUIStateBrg():BrgUIState = BrgUIState(
    barangEvent = this.toDetailBrgUiEvent(),
)