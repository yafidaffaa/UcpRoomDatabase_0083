package com.example.ucp2.ui.viewmodel.barang

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Barang
import com.example.ucp2.repository.RepositoryBrg
import com.example.ucp2.ui.navigasi.DestinasiDetailBrg
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class DetailBrgViewModel(
    savedStateHandle: SavedStateHandle,
    private val repositoryBrg: RepositoryBrg,
): ViewModel(){
    private val _id: String = checkNotNull(savedStateHandle[DestinasiDetailBrg.ID])

    val detailUIState: StateFlow<DetailBrgUIState> = repositoryBrg.getBrgById(_id)
        .filterNotNull()
        .map {
            DetailBrgUIState(
                detailBrgUiEvent = it.toDetailBrgUiEvent(),
                isLoading = false,
            )
        }
        .catch {
            emit(
                DetailBrgUIState(
                    isLoading = false,
                    isError = true,
                    errorMessage = it.message ?:"Terjadi kesalahan",
                )
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(2000),
            initialValue = DetailBrgUIState(
                isLoading = true,
            )
        )
    fun deleteBrg(){
        detailUIState.value.detailBrgUiEvent.toBarangEntity().let{
            viewModelScope.launch {
                repositoryBrg.deleteBrg(it)
            }
        }
    }
}

data class DetailBrgUIState(
    val detailBrgUiEvent: BarangEvent = BarangEvent(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String = ""
){
    val isUiEventEmpty: Boolean
        get() = detailBrgUiEvent == BarangEvent()

    val isUiEventNotEmpty: Boolean
        get() = detailBrgUiEvent != BarangEvent()
}

fun Barang.toDetailBrgUiEvent(): BarangEvent{
    return BarangEvent(
        id = id,
        nama = nama,
        deskripsi = deskripsi,
        harga = harga,
        stok = stok,
        nama_supplier = nama_supplier
    )
}