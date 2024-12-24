package com.example.ucp2.ui.view.barang

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.data.SupplierName
import com.example.ucp2.ui.customwidget.DropDownSpl
import com.example.ucp2.ui.customwidget.TopAppBar
import com.example.ucp2.ui.viewmodel.PenyediaViewModel
import com.example.ucp2.ui.viewmodel.barang.BarangEvent
import com.example.ucp2.ui.viewmodel.barang.BrgUIState
import com.example.ucp2.ui.viewmodel.barang.FormBrgErrorState
import com.example.ucp2.ui.viewmodel.barang.InsertBrgViewModel
import kotlinx.coroutines.launch

@Composable
fun InsertBrgView(
    onBack: () -> Unit,
    onNavigate: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: InsertBrgViewModel = viewModel(factory = PenyediaViewModel.Factory)//initial viewmodel
) {
    val uiState = viewModel.uiState
    val snackbarHostState = remember { SnackbarHostState() }
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(uiState.snackbarMessage) {
        uiState.snackbarMessage?.let { message ->
            coroutineScope.launch {
                snackbarHostState.showSnackbar(message)
                viewModel.resetBrgSnackBarMessage()
            }
        }
    }

    Scaffold(
        modifier = modifier,
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
        topBar = {
            TopAppBar(
                onBack = onBack,
                showBackButton = true,
                judul = "Toko Murah Jaya",
                description = "Tambah Barang",
                modifier = modifier
                    .fillMaxWidth()
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 120.dp, start = 20.dp, end = 20.dp, bottom = 20.dp)
        ) {
            InsertBodyBrg(

                uiState = uiState,
                onValueChange = { updateEvent ->
                    viewModel.updateState(updateEvent)
                },
                onClick = {
                    viewModel.saveDataBrg() {
                        onNavigate()
                    }
                }
            )
        }
    }
}


@Composable
fun InsertBodyBrg(
    onValueChange: (BarangEvent) -> Unit,
    uiState: BrgUIState,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FormBarang(
            barangEvent = uiState.barangEvent,
            onValueChange = onValueChange,
            errorState = uiState.isEntryValid,
            modifier = Modifier
                .fillMaxWidth()

        )
        Button(
            onClick = onClick,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
        ) {
            Text(text = "Simpan", color = Color.White)
        }
    }
}


@Composable
fun FormBarang(
    barangEvent: BarangEvent = BarangEvent(),
    onValueChange: (BarangEvent) -> Unit = {},
    errorState: FormBrgErrorState = FormBrgErrorState(),
    modifier: Modifier = Modifier

) {

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = barangEvent.id,
            onValueChange = { onValueChange(barangEvent.copy(id = it)) },
            label = { Text("ID") },
            isError = errorState.id != null,
            placeholder = { Text("Masukkan ID") }

        )

        Text(
            text = errorState.id ?: "",
            color = Color.Red

        )


        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = barangEvent.nama,
            onValueChange = { onValueChange(barangEvent.copy(nama = it)) },
            label = { Text("Nama") },
            isError = errorState.nama != null,
            placeholder = { Text("Masukkan Nama") }

        )

        Text(
            text = errorState.nama ?: "",
            color = Color.Red

        )

        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = barangEvent.deskripsi,
            onValueChange = { onValueChange(barangEvent.copy(deskripsi = it)) },
            label = { Text("Deskripsi") },
            isError = errorState.deskripsi != null,
            placeholder = { Text("Masukkan Deskripsi") }

        )

        Text(
            text = errorState.deskripsi ?: "",
            color = Color.Red

        )

        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = barangEvent.harga,
            onValueChange = { onValueChange(barangEvent.copy(harga = it)) },
            label = { Text("Harga") },
            isError = errorState.harga != null,
            placeholder = { Text("Masukkan Harga") }

        )

        Text(
            text = errorState.harga ?: "",
            color = Color.Red

        )

        OutlinedTextField(
            modifier = modifier.fillMaxWidth(),
            value = barangEvent.stok,
            onValueChange = { onValueChange(barangEvent.copy(stok = it)) },
            label = { Text("Stok") },
            isError = errorState.stok != null,
            placeholder = { Text("Masukkan Stok") }

        )

        Text(
            text = errorState.stok ?: "",
            color = Color.Red

        )

        Spacer(modifier = Modifier.height(10.dp))
        DropDownSpl(
            selectedValue = barangEvent.nama_supplier,
            names = SupplierName.NamaSpl(),
            label = "Nama Supplier",
            onValueChange = { selectedValue ->
                onValueChange(barangEvent.copy(nama_supplier = selectedValue))
            }
        )
        if (errorState.nama_supplier != null) {
            Text(
                text = errorState.nama_supplier!!,
                color = Color.Red,
                fontSize = 15.sp,
                modifier = Modifier
            )
        }
        Spacer(modifier = Modifier.height(20.dp))
    }
}

