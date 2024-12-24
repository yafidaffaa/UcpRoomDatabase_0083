package com.example.ucp2.ui.viewmodel.supplier

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ucp2.data.entity.Supplier
import com.example.ucp2.repository.RepositorySpl
import kotlinx.coroutines.launch



data class SupplierEvent(
    var id: String = "",
    var nama: String = "",
    var kontak: String = "",
    var alamat: String = "",
)