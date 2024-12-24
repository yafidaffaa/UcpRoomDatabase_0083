package com.example.ucp2.ui.view.supplier

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.ui.customwidget.TopAppBar
import com.example.ucp2.ui.viewmodel.PenyediaViewModel
import com.example.ucp2.ui.viewmodel.supplier.FormSplErrorState
import com.example.ucp2.ui.viewmodel.supplier.InsertSplViewModel
import com.example.ucp2.ui.viewmodel.supplier.SplUIState
import com.example.ucp2.ui.viewmodel.supplier.SupplierEvent
import kotlinx.coroutines.launch





@Composable
fun FormSupplier(
    supplierEvent: SupplierEvent = SupplierEvent(),
    onValueChange: (SupplierEvent) -> Unit = {},
    errorState: FormSplErrorState = FormSplErrorState(),
    modifier: Modifier = Modifier

) {

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = supplierEvent.id,
            onValueChange = { onValueChange(supplierEvent.copy(id = it)) },
            label = { Text("ID") },
            isError = errorState.id != null,
            placeholder = { Text("Masukan ID") }

        )

        Text(
            text = errorState.id ?: "",
            color = Color.Red

        )


        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = supplierEvent.nama,
            onValueChange = { onValueChange(supplierEvent.copy(nama = it)) },
            label = { Text("Nama") },
            isError = errorState.nama != null,
            placeholder = { Text("Masukan Nama") }

        )

        Text(
            text = errorState.nama ?: "",
            color = Color.Red

        )

        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = supplierEvent.kontak,
            onValueChange = { onValueChange(supplierEvent.copy(kontak = it))},
            label = { Text("Kontak") },
            isError = errorState.kontak != null,
            placeholder = { Text("Masukan Kontak") }

        )

        Text(
            text = errorState.kontak ?: "",
            color = Color.Red

        )

        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = supplierEvent.alamat,
            onValueChange = { onValueChange(supplierEvent.copy(alamat = it)) },
            label = { Text("Alamat") },
            isError = errorState.alamat != null,
            placeholder = { Text("Masukan Alamat") }

        )

        Text(
            text = errorState.alamat ?: "",
            color = Color.Red

        )
    }
}