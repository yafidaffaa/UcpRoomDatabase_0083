package com.example.ucp2.ui.view.supplier

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.data.entity.Supplier
import com.example.ucp2.ui.customwidget.TopAppBar
import com.example.ucp2.ui.viewmodel.PenyediaViewModel
import com.example.ucp2.ui.viewmodel.supplier.HomeSplUIState
import com.example.ucp2.ui.viewmodel.supplier.HomeSplViewModel
import kotlinx.coroutines.launch
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

@Composable
fun HomeSplView(
    viewModelSpl: HomeSplViewModel = viewModel(factory = PenyediaViewModel.Factory),
    onAddSpl: () -> Unit = { },
    onBack: () -> Unit,
    modifier: Modifier = Modifier
){
    Scaffold(
        topBar = {
            TopAppBar(
                judul = "Toko Murah Jaya",
                description = "Daftar Supplier",
                showBackButton = true,
                onBack = onBack,
                modifier = modifier
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddSpl,
                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.padding(16.dp),
                containerColor = Color.Black
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Tambah Supplier",
                    tint = Color.White
                )
            }
        }
    ) {innerPadding ->
        val homeSplUIState by viewModelSpl.homeSplUIState.collectAsState()

        BodyHomeSplView(
            homeSplUIState = homeSplUIState,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BodyHomeSplView(
    homeSplUIState: HomeSplUIState,
    onClick: (String) -> Unit = { },
    modifier: Modifier = Modifier
){
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    when{
        homeSplUIState.isLoading -> {
            Box(
                modifier=modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ){
                CircularProgressIndicator()
            }
        }
        homeSplUIState.isError -> {
            LaunchedEffect(homeSplUIState.errorMessage) {
                homeSplUIState.errorMessage?.let {message ->
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(message)
                    }
                }
            }
        }
        homeSplUIState.listSpl.isEmpty() -> {
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Tidak ada data Supplier.",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        else -> {
            ListSupplier(
                listSpl = homeSplUIState.listSpl,
                onClick = {
                    onClick(it)
                    println(
                        it
                    )
                },
                modifier = modifier
            )
        }
    }
}

@Composable
fun ListSupplier(
    listSpl: List<Supplier>,
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit = { }
){
    LazyColumn(
        modifier = modifier
    ) {
        items(
            items = listSpl,
            itemContent = {spl ->
                CardSpl(
                    spl=spl,
                )
            }
        )
    }
}

@Composable
fun CardSpl(
    spl: Supplier,
    modifier: Modifier = Modifier,

) {
    Card (

        modifier = modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clip(MaterialTheme.shapes.medium),
        colors = CardDefaults.cardColors(containerColor = Color.DarkGray)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Lock,
                    contentDescription = "ID Icon",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Yellow
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = spl.id,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Nama Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Person,
                    contentDescription = "Nama Icon",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Yellow
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = spl.nama,
                    fontWeight = FontWeight.Bold,
                    fontSize = 16.sp,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            // Kontak Row
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Call,
                    contentDescription = "Kontak Icon",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Yellow
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = spl.kontak,
                    fontWeight = FontWeight.Normal,
                    color = Color.White
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.LocationOn,
                    contentDescription = "Alamat Icon",
                    modifier = Modifier.size(24.dp),
                    tint = Color.Yellow
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = spl.alamat,
                    fontWeight = FontWeight.Normal,
                    color = Color.White
                )
            }
        }
    }
}
