package com.example.ucp2.data

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.ui.viewmodel.PenyediaViewModel
import com.example.ucp2.ui.viewmodel.supplier.HomeSplViewModel

object SupplierName {
    @Composable
    fun NamaSpl (
        SupplierNameViewModel : HomeSplViewModel = viewModel(factory = PenyediaViewModel.Factory)
    ): List<String> {
        val datanama = SupplierNameViewModel.homeSplUIState.collectAsState()
        val listSpl = datanama.value.listSpl
        return listSpl.map {it.nama}
    }
}