package com.example.ucp2.ui.view

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.ucp2.ui.customwidget.TopAppBar
import com.example.ucp2.ui.viewmodel.PenyediaViewModel
import com.example.ucp2.ui.viewmodel.supplier.HomeSplViewModel

@Composable

fun HomeTokoView (
    modifier: Modifier = Modifier,
    onAddSpl: () -> Unit = { },
    onAddBrg: () -> Unit = { },
    viewModel: HomeSplViewModel = viewModel(factory = PenyediaViewModel.Factory),

) {
    Scaffold (
        topBar = {
            TopAppBar(
                judul = "Toko Murah Jaya",
                description = "Management Barang & Supplier",
                showBackButton = false,
                onBack = { },
                modifier = modifier
            )
        },
        content = { paddingValues ->
            BodyHomeView(
                onAddSpl = onAddSpl,
                onAddBrg = onAddBrg,
                paddingValues = paddingValues
            )
        }
    )
}

@Composable
fun BodyHomeView (
    onAddBrg: () -> Unit,
    onAddSpl: () -> Unit,
    paddingValues: PaddingValues,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(1),
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddingValues)
    ) {
        item {
            HomeCard(
                title = "Management Supplier",
                icon = Icons.Filled.Person,
                onClick = onAddSpl,
                gradient = Brush.verticalGradient(
                    colors = listOf(
                        Color.Black,
                        Color(0xFF4B6F82)
                    )
                )
            )
        }
        item {
            HomeCard(
                title = "Management Barang",
                icon = Icons.Filled.ShoppingCart,
                onClick = onAddBrg,
                gradient = Brush.verticalGradient(
                    colors = listOf(
                        Color.Black,
                        Color(0xFF4B6F82)
                    )
                )
            )
        }
    }
}

@Composable
fun HomeCard (
    title: String,
    icon: ImageVector,
    gradient: Brush,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var clicked by remember { mutableStateOf(false) }

    val scale = animateFloatAsState(
        targetValue = if (clicked) 0.95f else 1f,
        animationSpec = tween(durationMillis = 300),
        label = ""
    )

    Card(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                clicked = !clicked
                onClick()
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.Black
        )
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(brush = gradient)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = Color.White,
                    modifier = Modifier
                        .padding(16.dp)
                        .size(60.dp)
                )

                Spacer(modifier = Modifier.padding(4.dp))

                Text(
                    text = title,
                    color = Color.White,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally)
                )
            }
        }
    }
}